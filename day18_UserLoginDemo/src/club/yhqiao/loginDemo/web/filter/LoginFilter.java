package club.yhqiao.loginDemo.web.filter;

import club.yhqiao.loginDemo.domain.Admin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // transform

        HttpServletRequest request = (HttpServletRequest)req;
        String uri = request.getRequestURI();

//        if(uri.contains("/login.jsp")||uri.contains("/loginServlet")
//                ||uri.contains("/css/")||uri.contains("/js/")
//                ||uri.contains("/fonts/")||uri.contains("/checkCodeServlet")) {
//            chain.doFilter(req,resp);
//        } else {
//            Object admin = request.getSession().getAttribute("admin");
//            if(admin != null) {
//                chain.doFilter(req,resp);
//            } else { //
//                request.setAttribute("login_msg","please login first");
//                request.getRequestDispatcher("/login.jsp").forward(request,resp);
//            }
//        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
