package club.yhqiao.web.servlet;

import club.yhqiao.dao.UserDao;
import club.yhqiao.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");


//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        User LoginUser = new User();
//        LoginUser.setUsername(username);
//        LoginUser.setPassword(password);

        Map<String, String[]> parameterMap = request.getParameterMap();
        User LoginUser  = new User();

        try {
            BeanUtils.populate(LoginUser,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        UserDao userDao = new UserDao();
        User user = userDao.login(LoginUser);
        if(user == null) {
            request.getRequestDispatcher("/FailServlet")
                    .forward(request,response);
        } else {
            request.setAttribute("user",user);
            request.getRequestDispatcher("/SuccessServlet").forward(request,response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
