package club.yhqiao.loginDemo.web.servlet;

import club.yhqiao.loginDemo.domain.Admin;
import club.yhqiao.loginDemo.domain.User;
import club.yhqiao.loginDemo.service.UserService;
import club.yhqiao.loginDemo.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Random;

/**
 * 验证码
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String verifycode = request.getParameter("verifycode");

		//verify checkcode
		HttpSession session = request.getSession();
		String checkcode_server =(String) session.getAttribute("CHECKCODE_SERVER");

		if(!checkcode_server.equalsIgnoreCase(verifycode)) {
			request.setAttribute("login_msg","checkcode_ERROR");
			request.getRequestDispatcher("/login.jsp").forward(request,response);
			return;
		}


		Map<String, String[]> map = request.getParameterMap();

		// get user
		Admin admin = new Admin();
		try {
			BeanUtils.populate(admin,map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}


		// use service to query
		UserService userService = new UserServiceImpl();
		Admin loginAdmin = userService.login(admin);
		if(loginAdmin!=null) {
			session.setAttribute("admin",loginAdmin);
			response.sendRedirect(request.getContextPath()+"/query.jsp");

		} else {
			request.setAttribute("login_msg","username_or_password_ERROR");
			request.getRequestDispatcher("/login.jsp").forward(request,response);
		}


		// whether success
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request,response);
	}
}



