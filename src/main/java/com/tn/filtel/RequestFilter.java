package com.tn.filtel;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RequestFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;
        //  /page/login.jsp
        String uri = request.getRequestURI();
        String path=uri.substring(uri.lastIndexOf("/")+1);

        if(path.equals("login.jsp")||path.equals("regs")||path.equals("login")||path.equals("getHouseByPass")||path.equals("list.jsp")||path.equals("regs.jsp")||path.equals("checkUserName")) {
            chain.doFilter(req, resp);
        }else {
            HttpSession session = request.getSession();
            Object users = session.getAttribute("users");
            if(users==null){
               // response.sendRedirect("login.jsp");
            }else {
                chain.doFilter(req, resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
