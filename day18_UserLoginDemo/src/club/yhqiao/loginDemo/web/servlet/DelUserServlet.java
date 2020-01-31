package club.yhqiao.loginDemo.web.servlet;

import club.yhqiao.loginDemo.service.UserService;
import club.yhqiao.loginDemo.service.impl.UserServiceImpl;
import com.sun.xml.internal.ws.api.model.wsdl.editable.EditableWSDLPart;
import sun.awt.geom.AreaOp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delUserServlet")
public class DelUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        UserService userService = new UserServiceImpl();
        userService.DelUserById(id);

        response.sendRedirect(request.getContextPath()+"/findUserByPageServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
