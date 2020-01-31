package club.yhqiao.test;

import club.yhqiao.dao.UserDao;
import club.yhqiao.domain.User;
import org.junit.Test;

public class UserDaoTest {
    @Test
    public void testLogin() {
        User loginuser = new User();
        loginuser.setId(1);
        loginuser.setUsername("qyh");
        loginuser.setPassword("450096");

        UserDao userDao= new UserDao();
        User user = userDao.login(loginuser);
        System.out.println(user);

    }
}
