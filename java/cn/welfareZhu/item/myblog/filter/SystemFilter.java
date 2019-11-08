package cn.welfareZhu.item.myblog.filter;

import cn.welfareZhu.item.myblog.model.ArticleImg;

import static cn.welfareZhu.item.myblog.util.SystemIdentifiers.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SystemFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest)request;
        HttpServletResponse httpServletResponse=(HttpServletResponse)response;
        HttpSession session=httpServletRequest.getSession(true);
        session.setMaxInactiveInterval(3600);
        if (session.getAttribute(LOGIN_STATUS)==null){
            session.setAttribute(LOGIN_STATUS,TOURIST);
        }
        String requestUrl = httpServletRequest.getScheme() //当前链接使用的协议
                +"://" + httpServletRequest.getServerName()//服务器地址
                + ":" + httpServletRequest.getServerPort() //端口号
                + httpServletRequest.getContextPath();
        session.setAttribute(WEB_ROOT_URL,requestUrl);
        if (session.getAttribute(ARTICLE_IMG_CONTAINER)==null){
            List<ArticleImg> articleImgs=new ArrayList<>();
            session.setAttribute(ARTICLE_IMG_CONTAINER,articleImgs);
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
