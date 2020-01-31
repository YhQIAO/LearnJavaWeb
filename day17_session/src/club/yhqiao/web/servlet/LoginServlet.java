package club.yhqiao.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkcode = request.getParameter("checkcode");

        // get checkcode
        HttpSession session = request.getSession();
        String checkCode_session = (String)request.getSession().getAttribute("checkCode_session");

        // System.out.println(checkCode_session);
        session.removeAttribute("checkCode_session");

        if(checkCode_session!=null && checkCode_session.equalsIgnoreCase(checkcode)) {
            if("qyh".equalsIgnoreCase(username) && "123".equalsIgnoreCase(password)) {
                // get userInfo
                //
                session.setAttribute("user",username);
                response.sendRedirect(request.getContextPath()+"/success.jsp");
            } else {
                request.setAttribute("login_error","username_or_password_error");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }

        } else {
            request.setAttribute("cc_error","checkdode_error");
            request.getRequestDispatcher("/login.jsp").forward(request,response);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
