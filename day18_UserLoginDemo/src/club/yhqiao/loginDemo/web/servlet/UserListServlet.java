package club.yhqiao.loginDemo.web.servlet;

import club.yhqiao.loginDemo.domain.User;
import club.yhqiao.loginDemo.service.UserService;
import club.yhqiao.loginDemo.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.geom.RectangularShape;
import java.io.IOException;
import java.util.List;

@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // do UserService to query
        UserService service  = new UserServiceImpl();
        List<User> users = service.findAll();
        request.setAttribute("users",users);
         //
        request.getRequestDispatcher("/list.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
