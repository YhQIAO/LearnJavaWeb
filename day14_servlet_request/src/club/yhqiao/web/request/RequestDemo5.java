package club.yhqiao.web.request;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

@WebServlet("/requestdemo5")
public class RequestDemo5 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //获取请求信息的字节流
        BufferedReader reader = request.getReader();
        String line;
        while((line  = reader.readLine())!=null) {
            System.out.println(line);
        }

        // 根据参数名称获取参数值
        String username = request.getParameter("username");
        System.out.println("username is "+username);


        // 获取所有的参数名称
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            System.out.println(name);
            String value = request.getParameter(name);
            System.out.println(value);
            System.out.println("----------");
        }

        // 获取所有参数的Map集合
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> keySet = parameterMap.keySet();
        for(String name:keySet) {
            System.out.println(name);
            String[] values = parameterMap.get(name);
            for(String value:values) {
                System.out.println(value);
            }
            System.out.println("--------");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
