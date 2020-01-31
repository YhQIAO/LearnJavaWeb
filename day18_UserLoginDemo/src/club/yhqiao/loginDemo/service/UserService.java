package club.yhqiao.loginDemo.service;

import club.yhqiao.loginDemo.domain.Admin;
import club.yhqiao.loginDemo.domain.PageBean;
import club.yhqiao.loginDemo.domain.User;

import java.util.List;
import java.util.Map;

/*
 user management interface
 */
public interface UserService {

    public List<User> findAll();

    public Admin login(Admin admin);

    public void addUser(User user);

    public void DelUserById(String id);

    public User findUserById(String id);

    void updateUser(User user);

    void delSelectedUser(String[] uids);

    PageBean<User> findUserByPage(String currentPage, String rows);

    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);

    void registUser(Admin admin);
}
