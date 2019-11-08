package cn.welfareZhu.item.myblog.util;

public class SystemIdentifiers {
    // -1 用户登陆状态异常
    //  0 登陆成功
    //  1 用户未登录

    //用户权限
    public static final int TOURIST=0;  //游客
    public static final int USER=1;  //用户
    public static final int MANAGER=2;  //管理员
    public static final int HOST=3;  //超级管理员

    //用户状态/对象状态
    public static final int TO_AUDIT=0;  //待审核
    public static final int NORMAL=1;  //正常使用
    public static final int BANNED=2;  //被封禁
    //审核状态
    public static final int PASSED=3;  //已通过
    public static final int UN_PASS=4;  //未通过
    //用户状态
    public static final int LOG_OUT=5;  //被注销
    //对象状态
    public static final int DELETED=6;  //被删除

    //对象权限
    public static final int IS_PUBLIC=0;  //公开
    public static final int IS_PRIVATE=1;  //私有

    //性别标识
    public static final int IS_MALE=0;  //男性
    public static final int IS_FEMALE=1;  //女性

    //通知状态
    public static final int WAIT_PROCESS=0;  //未处理
    public static final int PROCESSED=1;  //已处理
    public static final int IN_DATE=2;  //需要阅览
    public static final int OUT_DATE=3;  //已过期

    //通知权限
    public static final int AUTH_PUBLIC=0;  //公共
    public static final int AUTH_MANAGE=1;  //管理员
    public static final int AUTH_SINGLE=2;  //私有

    //通知类型
    public static final int TIER_ARTICLE_COMMENT=0;  //文章评论层
    public static final int TIER_ARTICLE_REPLY=1;  //文章回复层
    public static final int TIER_LINK_COMMENT=2;  //收藏链接评论层
    public static final int TIER_LINK_REPLY=3;  //收藏链接回复层
    public static final int TIER_FILE_COMMENT=4;  //文件评论层
    public static final int TIER_FILE_REPLY=5;  //文件回复层
    public static final int TIER_MESSAGE=6;  //留言层
    public static final int TIER_MESSAGE_REPLY=7;  //留言回复
    public static final int TIER_NOTICE=8;  //通知层
    public static final int TIER_REPORT=9;  //举报层
    public static final int TIER_ACCOUNT_AUDIT=10;  //账号审核
    public static final int TIER_ARTICLE_AUDIT=11;  //文章审核
    public static final int TIER_LINK_AUDIT=12;  //收藏链接审核
    public static final int TIER_FILE_AUDIT=13;  //文件审核

    //评论/回复
    public static final int COMMENT=0;  //评论
    public static final int REPLY=1;  //回复

    //留言/回复
    public static final int MESSAGE=0;  //留言
    public static final int USER_REPLY=1;  //用户（主人）回复
    public static final int VISITOR_REPLY=2;  //访客回复

    //是否是游客
    public static final int IS_TOURIST=0;  //是
    public static final int NOT_TOURIST=1;  //否

    //文件类型
    public static final int JPG_FILE=0;  //.jpg
    public static final int PNG_FILE=1;  //.png
    public static final int GIF_FILE=2;  //.gif
    public static final int ZIP_FILE=3;  //.zip
    public static final int RAR_FILE=4;  //.rar
    public static final int TXT_FILE=5;  //.txt
    public static final int DOCX_FILE=6;  //.docx
    public static final int XLS_FILE=7;  //.xlsx
    public static final int PDF_FILE=8;  //.pdf
    public static final int MP3_FILE=9;  //.mp3
    public static final int OGG_FILE=10;  //.ogg

    public static final String LOGIN_STATUS="loginStatus";  //登陆状态
    public static final int LOGIN_SUCC=0;  //登陆成功
    public static final int LOGIN_FAIL=1;  //登陆失败
    public static final String LOGIN_USER="loginUser";  //登陆用户
    public static final String MODEL_MESSAGE="modelMessage";  //通用消息标识
    public static final String USER_INFO="userInfo";  //用户信息标识
    public static final String USER_SEARCH="userSearch";

    public static final int IS_OK=0;  //成功
    public static final int IS_NOT_OK=1;  //失败
    public static final int QUERY_FAIL=404;  //未找到

    public static final String SYS_FOLDER="sys";  //sys路径
    public static final String USER_FOLDER_PREFFIX="u";  //用户资源文件夹前缀
    public static final String PORTRAIT_FOLDER="portrait";  //头像资源文件夹
    public static final String ARTICLE_FOLDER="article";  //文章资源文件夹
    public static final String FILE_FOLDER="file";  //文件资源文件夹

    //Redis的Key值前缀
    public static final String ART_COM_KRY_PREFFIX="ac";  //文章评论前缀
    public static final String LINK_COM_KEY_PREFFIX="lc";  //链接评论前缀
    public static final String FILE_COM_KEY_PREFFIX="fc";  //文件评论前缀
    public static final String ART_LIKE_KEY_PREFFIX="al";  //文章点赞前缀
    public static final String LINK_LIKE_KEY_PREFFIX="ll"; //链接点赞前缀
    public static final String FILE_LIKE_KEY_PREFFIX="fl";  //文件点赞前缀

    public static final String WEB_ROOT_URL="webRootUrl";  //项目访问ROOT URL

    public static final String USER_NOTIFY_NUM="userNotifyNum";  //用户通知数量
    public static final String MANAGER_NOTIFY_NUM="managerNotifyNum";  //管理员通知数量

    public static final String ARTICLE_IMG_CONTAINER="articleImageContainer";  //文章上传图片容器

    public static final String TO_AUDIT_PART="toAuditPart";  //待审核部分
    public static final String NORMAL_PART="normalPart";  //已审核部分

    public static final int PAGE_SIZE=10;  //页面承载的记录数量

    public static final String ACCESS_USER="accessUser";  //被访问的用户对象

    public static final int LIKED=0;  //点赞成功
    public static final int ALREADY_LIKE=1;  //点赞失败，已经赞过
    public static final int LOGIN_STATUS_ERROR=2;  //点赞失败，登陆状态异常

    public static final String ARTICLE_BODY="articleBody";  //文章实体
    public static final String ARTICLE_COMMENT_LIST="artComList";  //文章评论列表

    public static final int COM_SIZE=5;  //页面显示的评论数量
    public static final int REPLY_SIZE=3;  //每条评论现实的回复数量

}
