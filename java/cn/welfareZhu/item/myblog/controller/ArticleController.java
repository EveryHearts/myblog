package cn.welfareZhu.item.myblog.controller;

import static cn.welfareZhu.item.myblog.util.SystemIdentifiers.*;

import cn.welfareZhu.item.myblog.dto.ArticleCommentDTO;
import cn.welfareZhu.item.myblog.dto.ArticleDTO;
import cn.welfareZhu.item.myblog.dto.LabelDTO;
import cn.welfareZhu.item.myblog.dto.UserSearchDTO;
import cn.welfareZhu.item.myblog.model.ArticleComment;
import cn.welfareZhu.item.myblog.model.ArticleImg;
import cn.welfareZhu.item.myblog.model.ArticleInfo;
import cn.welfareZhu.item.myblog.model.LabelInfo;
import cn.welfareZhu.item.myblog.mq.ArticleLikeKeyProducer;
import cn.welfareZhu.item.myblog.service.ArticleCommentService;
import cn.welfareZhu.item.myblog.service.ArticleService;
import cn.welfareZhu.item.myblog.service.LabelService;
import cn.welfareZhu.item.myblog.service.UserService;
import cn.welfareZhu.item.myblog.util.QueryPageMap;
import cn.welfareZhu.item.myblog.util.QueryResult;
import cn.welfareZhu.item.myblog.vo.*;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author 朱剑飞
 * @name 文章Controller
 * @date 2019-09-27
 * **/
@Controller
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;
    @Resource
    private LabelService labelService;
    @Resource
    private ArticleCommentService articleCommentService;
    @Resource
    private UserService userService;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private ArticleLikeKeyProducer articleLikeKeyProducer;

    private static Destination destination=new ActiveMQQueue("cn.welfareZhu.item.myblog.articleLike");

    //跳转到文章编辑上传页面
    @RequestMapping("/editorPage")
    public String gotoEditorArticle(Model model,HttpSession session){
        if ((int)session.getAttribute(LOGIN_STATUS)!=USER){
            model.addAttribute(MODEL_MESSAGE,"请登陆后再次尝试本操作");
            return "home";
        }
        UserSearchDTO user=(UserSearchDTO)session.getAttribute(LOGIN_USER);
        if (user.getUserStatus()!=NORMAL){
            model.addAttribute(MODEL_MESSAGE,"你的账户还未经过审核,请联系管理员");
            return "home";
        }
        return "article/editorUpload";
    }

    //上传并返回文章图片信息
    @PostMapping("/uploadArticleImage")
    @ResponseBody
    public Object addNewArticleImage(@RequestParam("file") MultipartFile image, HttpSession session) throws IOException {
        if ((int)session.getAttribute(LOGIN_STATUS)!=USER){
            return IS_NOT_OK;
        }
        if (image==null||image.getSize()<=0){
            return IS_NOT_OK;
        }
        UserSearchDTO user=(UserSearchDTO)session.getAttribute(LOGIN_USER);
        String imageFolder=session.getServletContext().getRealPath("/")
                +SYS_FOLDER+ File.separator
                +USER_FOLDER_PREFFIX+user.getUserId()+File.separator
                +ARTICLE_FOLDER;
        File baseDir=new File(imageFolder);
        if (!baseDir.exists()&&!baseDir.isDirectory()){
            if (!baseDir.mkdirs()){
                return IS_NOT_OK;
            }
        }
        String webRootUrl=(String)session.getAttribute(WEB_ROOT_URL);
        String basePath=baseDir+File.separator;
        String newImageName= FilenameUtils.getBaseName(image.getOriginalFilename())
                +System.currentTimeMillis()+"."
                +FilenameUtils.getExtension(image.getOriginalFilename());
        String newImagePath=basePath+newImageName;
        String newImageUrl=webRootUrl+
                "/"+SYS_FOLDER+
                "/"+USER_FOLDER_PREFFIX+user.getUserId()+
                "/"+ARTICLE_FOLDER+
                "/"+newImageName;
        byte[] imageByte=new byte[(int)image.getSize()];
        InputStream inputStream=image.getInputStream();
        inputStream.read(imageByte);
        FileUtils.writeByteArrayToFile(new File(newImagePath),imageByte,false);
        ArticleImg img=new ArticleImg();
        img.setImgByte(imageByte);
        img.setImgSrc(newImageUrl);
        img.setCreateDate(new Date());
        img.setImgStatus(TO_AUDIT);
        List imgList=(List)session.getAttribute(ARTICLE_IMG_CONTAINER);
        imgList.add(img);
        ArticleImageResultVO imageVO=new ArticleImageResultVO();
        imageVO.setCode(0);
        imageVO.setMsg("OK");
        ImageDataVO data=new ImageDataVO();
        data.setSrc(newImageUrl);
        data.setTitle(image.getOriginalFilename());
        imageVO.setData(data);
        return imageVO;
    }

    //检索并返回标签字段List
    @GetMapping("/queryArticleLabelValue")
    @ResponseBody
    public List<String> indexArticleLabel(@RequestParam("labelString") String value,HttpSession session){
        List<LabelDTO> labelDTOS=labelService.queryLabelByStringValue(value,0,5);
        if (labelDTOS.size()<=0){
            return null;
        }
        List<String> labelValues=new ArrayList<>();
        for (LabelDTO labelDTO:labelDTOS){
            labelValues.add(labelDTO.getLabelValue());
        }
        return labelValues;
    }

    //查询并返回指定标签值
    @GetMapping("/getArticleLabelId")
    @ResponseBody
    public int queryArticleLabelId(@RequestParam("labelValue") String labelValue){
        LabelDTO labelDTO=labelService.queryLabelByLabelValue(labelValue);
        if (labelDTO!=null){
            boolean isOk=labelService.increaseLabelUseNum(labelDTO.getLabelId());
            if (!isOk){
                return -1;
            }
            return labelDTO.getLabelId();
        }
        LabelInfo labelInfo=new LabelInfo();
        labelInfo.setLabelValue(labelValue);
        labelInfo.setUseNum(1);
        labelInfo.setLabelStatus(NORMAL);
        labelInfo.setCreateDate(new Date());
        int labelId=labelService.addNewLabel(labelInfo);
        if (labelId==0){
            return -1;
        }
        return labelId;
    }

    //上传文章
    @PostMapping("/uploadArticle")
    @ResponseBody
    public int addArticleInfo(ArticleInfo articleInfo,HttpSession session){
        if ((int)session.getAttribute(LOGIN_STATUS)!=USER){
            return IS_NOT_OK;
        }
        if (articleInfo==null){
            return IS_NOT_OK;
        }
        UserSearchDTO user=(UserSearchDTO)session.getAttribute(LOGIN_USER);
        List<ArticleImg> imgs=(List<ArticleImg>) session.getAttribute(ARTICLE_IMG_CONTAINER);
        articleInfo.setUserId(user.getUserId());
        articleInfo.setArticleStatus(TO_AUDIT);
        articleInfo.setLikeNum(0);
        articleInfo.setCreateDate(new Date());
        articleService.addNewArticle(articleInfo);
        if (imgs.size()>0){
            for (ArticleImg img:imgs){
                img.setArticleId(articleInfo.getArticleId());
                articleService.addNewArticleImg(img);
            }
        }
        imgs.clear();
        return IS_OK;
    }

    //浏览用户文章列表
    @GetMapping("/browseUserArticleList")
    public String browserUserArticleList(@RequestParam("userId") int id,@RequestParam("pageNo") int pageNo, Model model, HttpSession session){
        int userId;
        UserSearchDTO userSearch;
        if (id==0){
            if ((int)session.getAttribute(LOGIN_STATUS)==USER){
                UserSearchDTO userSearchDTO=(UserSearchDTO)session.getAttribute(LOGIN_USER);
                userId=userSearchDTO.getUserId();
                userSearch=userSearchDTO;
            }else {
                model.addAttribute(MODEL_MESSAGE,"请先选择你要查看的用户");
                return "home";
            }
        }else {
            userId=id;
            userSearch=userService.queryUserByUserId(userId);
        }
        boolean isHost=false;
        if ((int)session.getAttribute(LOGIN_STATUS)==USER){
            UserSearchDTO user=(UserSearchDTO)session.getAttribute(LOGIN_USER);
            if (userId==user.getUserId()){
                isHost=true;
            }
        }
        if (isHost){
            QueryPageMap<ArticleDTO> articleNormalMap = articleService.queryArticleByUserIdAndStatus(userId,NORMAL,pageNo,PAGE_SIZE);
            QueryPageMap<ArticleDTO> articleToAuditMap=articleService.queryArticleByUserIdAndStatus(userId,TO_AUDIT,pageNo,PAGE_SIZE);
            if (articleNormalMap!=null){
                List<ArticleDTO> articleNormals=articleNormalMap.getRecords();
                for (ArticleDTO article:articleNormals){
                    article.setContent(subStringArticleContent(article.getContent()));
                }
            }
            if (articleToAuditMap!=null){
                List<ArticleDTO> articleToAudits=articleToAuditMap.getRecords();
                for (ArticleDTO article:articleToAudits){
                    article.setContent(subStringArticleContent(article.getContent()));
                }
            }
            model.addAttribute(NORMAL_PART,articleNormalMap);
            model.addAttribute(TO_AUDIT_PART,articleToAuditMap);
        }else {
            QueryPageMap<ArticleDTO> articlePublicNormalMap=articleService.queryArticleByUserIdAndAuthAndStatus(userId,IS_PUBLIC,NORMAL,pageNo,PAGE_SIZE);
            if (articlePublicNormalMap!=null){
                List<ArticleDTO> articlePublicNormals=articlePublicNormalMap.getRecords();
                for (ArticleDTO article:articlePublicNormals){
                    article.setContent(subStringArticleContent(article.getContent()));
                }
            }
            model.addAttribute(NORMAL_PART,articlePublicNormalMap);
        }
        model.addAttribute(ACCESS_USER,userSearch);
        return "article/userArticleList";
    }

    //浏览公共文章列表
    @GetMapping("/browsePublicArticleList")
    public String browserPublicArticleList(@RequestParam("pageNo") int pageNo,Model model){
        QueryPageMap<ArticleDTO> articlePublicMap=articleService.queryArticleByAuthAndStatus(IS_PUBLIC,NORMAL,pageNo,PAGE_SIZE);
        if (articlePublicMap!=null){
            List<ArticleDTO> articlePublics=articlePublicMap.getRecords();
            for(ArticleDTO article:articlePublics){
                article.setContent(subStringArticleContent(article.getContent()));
            }
        }
        model.addAttribute(NORMAL_PART,articlePublicMap);
        return "article/publicArticleList";
    }

    //文章点赞
    @GetMapping("/praiseArticle")
    @ResponseBody
    public int praiseArticle(@RequestParam("articleId") int articleId,HttpSession session){
        if ((int)session.getAttribute(LOGIN_STATUS)!=USER){
            return LOGIN_STATUS_ERROR;
        }
        UserSearchDTO user=(UserSearchDTO)session.getAttribute(LOGIN_USER);
        ArticleLikeMoodVO articleLikeMoodVO=new ArticleLikeMoodVO();
        articleLikeMoodVO.setLikeKeyId(articleId);
        articleLikeMoodVO.setLikeUserId(user.getUserId());
        boolean isMember=redisTemplate.opsForSet().isMember(articleLikeMoodVO.getLikeKey(),articleLikeMoodVO.getLikeUserId());
        if (isMember){
            return ALREADY_LIKE;
        }
        articleLikeKeyProducer.sendMessage(destination,articleLikeMoodVO);
        return LIKED;
    }

    //浏览文章
    @GetMapping("/browseArticle")
    public String browserArticle(@RequestParam("articleId") int articleId,Model model,HttpSession session){
        QueryResult queryResult=articleService.queryArticleUAS(articleId);
        if (queryResult.getAuthValue()!=IS_PUBLIC){
            if ((int)session.getAttribute(LOGIN_STATUS)!=USER){
                model.addAttribute(MODEL_MESSAGE,"你不能查看别人的私有权限文章");
                return "home";
            }
            UserSearchDTO user=(UserSearchDTO)session.getAttribute(LOGIN_USER);
            if (user.getUserId()!=queryResult.getUserId()){
                model.addAttribute(MODEL_MESSAGE,"你没有权限查看该文章");
                return "home";
            }
        }
        ArticleDTO articleDTO=articleService.queryArticleByArticleId(articleId);
        model.addAttribute(ARTICLE_BODY,articleDTO);
        return "article/articleBrowser";
    }

    //删除文章
    @PostMapping("deleteArticle")
    @ResponseBody
    public int removeArticle(@RequestParam("articleId") int articleId,HttpSession session){
        if ((int)session.getAttribute(LOGIN_STATUS)!=USER){
            return IS_NOT_OK;
        }
        UserSearchDTO user=(UserSearchDTO)session.getAttribute(LOGIN_USER);
        QueryResult queryResult=articleService.queryArticleUAS(articleId);
        if (user.getUserId()!=queryResult.getUserId()){
            return IS_NOT_OK;
        }
        articleService.modifyArticleStatus(articleId,DELETED);
        articleService.modifyArticleImgStatus(articleId,DELETED);
        return IS_OK;
    }

    //添加文章
    @PostMapping("/addArticleComment")
    @ResponseBody
    public int addArticleComment(ArticleComment articleComment, HttpSession session){
        if ((int)session.getAttribute(LOGIN_STATUS)!=USER){
            return IS_NOT_OK;
        }
        UserSearchDTO user=(UserSearchDTO)session.getAttribute(LOGIN_USER);
        if (user.getUserStatus()!=NORMAL){
            return IS_NOT_OK;
        }
        QueryResult client=articleService.queryArticleUAS(articleComment.getArticleId());
        articleComment.setUserId(user.getUserId());
        articleComment.setCommentType(COMMENT);
        articleComment.setCommentStatus(NORMAL);
        articleComment.setClientId(client.getUserId());
        articleComment.setCreateDate(new Date());
        articleCommentService.addNewArticleComment(articleComment);
        return IS_OK;
    }

    //添加文章回复
    @PostMapping("/addArticleReply")
    @ResponseBody
    public int addArticleReply(ReplyVO artReply, HttpSession session){
        if ((int)session.getAttribute(LOGIN_STATUS)!=USER){
            return IS_NOT_OK;
        }
        UserSearchDTO user=(UserSearchDTO)session.getAttribute(LOGIN_USER);
        if (user.getUserStatus()!=NORMAL){
            return IS_NOT_OK;
        }
        ArticleComment articleReply=new ArticleComment();
        articleReply.setClientId(artReply.getClientId());
        articleReply.setArticleId(artReply.getMainObjectId());
        articleReply.setContent(artReply.getContent());
        articleReply.setUserId(user.getUserId());
        articleReply.setCommentType(REPLY);
        articleReply.setCommentStatus(NORMAL);
        articleReply.setCreateDate(new Date());
        articleCommentService.addNewArticleComment(articleReply);
        String articleCommentKey=ART_COM_KRY_PREFFIX+artReply.getCommentId();
        long replySize=redisTemplate.opsForZSet().size(articleCommentKey);
        double keyScore=Double.parseDouble(replySize+"");
        redisTemplate.opsForZSet().add(articleCommentKey,articleReply.getArticleCommentId(),keyScore);
        return IS_OK;
    }

    //请求文章评论页面
    @GetMapping("/browseArticleComment")
    public String browserArticleComment(@RequestParam("articleId") int articleId,@RequestParam("pageNo") int pageNo,Model model){
        model.addAttribute("articleId",articleId);
        QueryPageMap<ArticleCommentDTO> commentMap=articleCommentService.queryArticleCommentByArticleIdAndStatusType(articleId,NORMAL,COMMENT,pageNo,COM_SIZE);
        if (commentMap!=null){
            model.addAttribute("pageNum",commentMap.getPageNum());
            model.addAttribute("pageSize",commentMap.getPageSize());
            model.addAttribute("size",commentMap.getSize());
            model.addAttribute("pages",commentMap.getPages());
            List<ArticleCommentDTO> comments=commentMap.getRecords();
            System.out.println("comments size : "+comments.size());
            List<CommentReplyMap<ArticleCommentDTO>> commentContianer=new ArrayList<>();
            for (ArticleCommentDTO comment:comments){
                CommentReplyMap<ArticleCommentDTO> comReplyMap=new CommentReplyMap<>();
                comReplyMap.setComment(comment);
                System.out.println(comment.getContent());
                String commentKey=ART_COM_KRY_PREFFIX +comment.getArticleCommentId();
                long valueSize=redisTemplate.opsForZSet().size(commentKey);
                if (valueSize<=0){
                    commentContianer.add(comReplyMap);
                    continue;
                }
                double maxScore=Double.parseDouble(valueSize+"");
                double minScore=maxScore-REPLY_SIZE;
                List<ArticleCommentDTO> replies=new ArrayList<>();
                Set<Integer> replyIds=redisTemplate.opsForZSet().reverseRangeByScore(commentKey,minScore,maxScore);
                for (int replyId:replyIds){
                    ArticleCommentDTO reply=articleCommentService.queryArticleCommentByCommentId(replyId);
                    replies.add(reply);
                }
                comReplyMap.setReplies(replies);
                commentContianer.add(comReplyMap);
            }
            model.addAttribute("commentList",commentContianer);
            System.out.println(commentContianer.size());
        }else{
            model.addAttribute("commentList",null);
        }
        return "article/articleCommentBrowser";
    }

    //截取并包装返回文章前三段
    private String subStringArticleContent(String content){
        String[] cnt=content.split("\\<\\/p\\>");
        if (cnt.length==1){
            if (cnt[0].length()<=140){
                return cnt[0]+"</p>";
            }
            return cnt[0].substring(0,140)+"..."+"</p>";
        }
        if (cnt.length==2){
            if (cnt[0].length()>=140){
                return cnt[0].substring(0,140)+"</p>";
            }
            if ((cnt[0]+"</p>"+cnt[1]).length()<155){
                return (cnt[0]+"</p>"+cnt[1]+"</p>");
            }
            return (cnt[0]+"</p>"+cnt[1]).substring(0,155)+"</p>";
        }
        String cntString;
        if(cnt[0].length()>=140){
            return cnt[0].substring(0,140)+"</p>";
        }
        cntString=cnt[0]+"</p>"+cnt[1];
        if (cntString.length()>=155){
            return cntString.substring(0,155)+"</p>";
        }
        cntString=cnt[0]+"</p>"+cnt[1]+"</p>"+cnt[2];
        if (cntString.length()<170){
            return cntString+"</p>";
        }
        return cntString.substring(0,170)+"</p>";
    }
}
