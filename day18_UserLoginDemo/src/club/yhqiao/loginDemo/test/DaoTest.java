package club.yhqiao.loginDemo.test;

import club.yhqiao.loginDemo.dao.UserDao;
import club.yhqiao.loginDemo.dao.impl.UserDaoImpl;
import club.yhqiao.loginDemo.domain.Admin;
import club.yhqiao.loginDemo.domain.PageBean;
import club.yhqiao.loginDemo.domain.User;
import club.yhqiao.loginDemo.service.UserService;
import club.yhqiao.loginDemo.service.impl.UserServiceImpl;
import club.yhqiao.loginDemo.util.JDBCUtils;
import com.sun.javafx.logging.PulseLogger;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoTest {

    @Test
    public void TestDBConnection() {
        System.out.println(JDBCUtils.getDataSource());
    }

    @Test
    public void QueryTest() {
        UserService userService = new UserServiceImpl();
        List<User> users = userService.findAll();
        System.out.println(users);
    }

    @Test
    public void TestFind(){
        UserDao userDao = new UserDaoImpl();
        Admin admin = userDao.FindAdminByUsernameAndPassword("qyh", "450096");
        System.out.println(admin);
    }

    @Test
    public void TestAdd() {
        UserDao userDao = new UserDaoImpl();
        User user = new User();
        user.setAddress("江苏");
        user.setAge(20);
        user.setEmail("999999999@qq.com");
        user.setGender("男");
        user.setName("test");
        user.setQq("100001");
        for(int i = 0;i < 20;i++) {
        userDao.addUser(user);}
    }

    @Test
    public void findUserTest() {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.findUserById(100);
        System.out.println(user);
    }

    @Test
    public void findUserByPageTest() {
        UserService userService = new UserServiceImpl();
        PageBean<User> userByPage = userService.findUserByPage("3", "2");
        System.out.println(userByPage);
    }

    @Test
    public void findTotalCountTest() {
        UserDao userDao = new UserDaoImpl();
        Map<String,String[]> map = new HashMap<String, String[]>();
        String[] strings = new String[] {"te"};
        map.put("name",strings);

        int totalCount = userDao.findTotalCount(map);
        System.out.println(totalCount);
    }

    @Test
    public void findByPageTest() {
        UserDao userDao  = new UserDaoImpl();
        Map<String, String[]> map = new HashMap<>();
        String[] strings = new String[] {"t"};
        map.put("name",strings);

        List<User> users = userDao.findByPage(0, 5, map);
        System.out.println(users);
    }

    @Test
    public void registTest() {
        UserService userService = new UserServiceImpl();
        Admin admin = new Admin();
        admin.setUsername("test");
        admin.setPassword("test");
        userService.registUser(admin);
    }

}
