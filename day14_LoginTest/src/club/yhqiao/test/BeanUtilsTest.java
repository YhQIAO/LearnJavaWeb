package club.yhqiao.test;

import club.yhqiao.domain.User;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class BeanUtilsTest {

    @Test
    public void test() throws InvocationTargetException, IllegalAccessException {
        User user = new User();
        BeanUtils.setProperty(user,"username","qh");

        System.out.println(user);
    }
}
