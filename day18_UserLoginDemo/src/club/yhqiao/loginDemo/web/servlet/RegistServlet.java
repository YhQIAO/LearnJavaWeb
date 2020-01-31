package club.yhqiao.loginDemo.web.servlet;

import club.yhqiao.loginDemo.domain.Admin;
import club.yhqiao.loginDemo.service.UserService;
import club.yhqiao.loginDemo.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 验证码
 */
@WebServlet("/registServlet")
public class RegistServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		// 获取验证码
		String verifycode = request.getParameter("verifycode");

		//verify checkcode
		HttpSession session = request.getSession();
		String checkcode_server =(String) session.getAttribute("CHECKCODE_SERVER");

		if(!checkcode_server.equalsIgnoreCase(verifycode)) {
			request.setAttribute("login_msg","checkcode_ERROR");
			request.getRequestDispatcher("/regist.jsp").forward(request,response);
			return;
		}

		String password = request.getParameter("password");
		String confirmpassword = request.getParameter("confirmpassword");

		if(!password.equals(confirmpassword)) {
			request.setAttribute("login_msg","password_mismatch_ERROR");
			request.getRequestDispatcher("/regist.jsp").forward(request,response);
			return;
		}


		Map<String, String[]> map = request.getParameterMap();

		// get user
		Admin admin = new Admin();
		try {
			BeanUtils.populate(admin,map);
			System.out.println(admin);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}


		// use service to query

		UserService userService = new UserServiceImpl();

		userService.registUser(admin);

		response.sendRedirect(request.getContextPath()+"/success.jsp");


		// whether success
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request,response);
	}
}



