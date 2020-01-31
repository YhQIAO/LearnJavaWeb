package club.yhqiao.loginDemo.dao;


import club.yhqiao.loginDemo.domain.Admin;
import club.yhqiao.loginDemo.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    public List<User> findAll();

    public Admin FindAdminByUsernameAndPassword(String username, String password);

    public void addUser(User user);

    public void delUserById(int id);


    User findUserById(int parseInt);

    void updateUser(User user);

    int findTotalCount();

    List<User> findByPage(int start, int rows);

    int findTotalCount(Map<String, String[]> condition);

    List<User> findByPage(int start, int rows, Map<String, String[]> condition);


    void resigtAdmin(Admin admin);
}
