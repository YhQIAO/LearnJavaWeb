package club.yhqiao.loginDemo.service.impl;

import club.yhqiao.loginDemo.dao.UserDao;
import club.yhqiao.loginDemo.dao.impl.UserDaoImpl;
import club.yhqiao.loginDemo.domain.Admin;
import club.yhqiao.loginDemo.domain.PageBean;
import club.yhqiao.loginDemo.domain.User;
import club.yhqiao.loginDemo.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private UserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {

        //use dao to query

        return dao.findAll();
    }

    @Override
    public Admin login(Admin admin) {
        return dao.FindAdminByUsernameAndPassword(admin.getUsername(), admin.getPassword());
    }

    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    @Override
    public void DelUserById(String id) {
        dao.delUserById(Integer.parseInt(id));
    }

    @Override
    public User findUserById(String id) {
        return dao.findUserById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    @Override
    public void delSelectedUser(String[] uids) {
        if(uids!=null && uids.length>0) {
            for (String id : uids) {
                dao.delUserById(Integer.parseInt(id));
            }
        }
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        PageBean<User> pageBean = new PageBean<User>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setRows(rows);
        int totalCount = dao.findTotalCount();
        pageBean.setTotalCount(totalCount);
        int start = (currentPage-1)*rows;
        List<User> list = dao.findByPage(start,rows);
        pageBean.setList(list);
        int totalPage = (totalCount % rows == 0) ? totalCount/rows : totalCount/rows +1;
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {

        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        PageBean<User> pageBean = new PageBean<User>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setRows(rows);
        int totalCount = dao.findTotalCount(condition);
        pageBean.setTotalCount(totalCount);
        int start = (currentPage-1)*rows;
        List<User> list = dao.findByPage(start,rows,condition);
        pageBean.setList(list);
        int totalPage = (totalCount % rows == 0) ? totalCount/rows : totalCount/rows +1;
        pageBean.setTotalPage(totalPage);

        return pageBean;
    }

    @Override
    public void registUser(Admin admin) {
        dao.resigtAdmin(admin);
    }
}
