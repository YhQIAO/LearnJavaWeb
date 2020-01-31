package club.yhqiao.loginDemo.web.servlet;

import club.yhqiao.loginDemo.domain.PageBean;
import club.yhqiao.loginDemo.domain.User;
import club.yhqiao.loginDemo.service.UserService;
import club.yhqiao.loginDemo.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");

        if(currentPage == null || "".equals(currentPage)) {
            currentPage ="1";
        }

        if(Integer.parseInt(currentPage) <=0) {
            currentPage = "1";
        }

        if(rows == null || "".equals(rows)) {
            rows ="10";
        }

        Map<String, String[]> condition = request.getParameterMap();

        UserService userService = new UserServiceImpl();
        PageBean<User> pageBean = userService.findUserByPage(currentPage,rows,condition);

        request.setAttribute("pageBean",pageBean);
        request.setAttribute("condition",condition);

        request.getRequestDispatcher("/list.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
