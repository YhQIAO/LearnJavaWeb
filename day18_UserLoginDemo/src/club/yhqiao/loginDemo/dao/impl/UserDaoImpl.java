package club.yhqiao.loginDemo.dao.impl;

import club.yhqiao.loginDemo.dao.UserDao;
import club.yhqiao.loginDemo.domain.Admin;
import club.yhqiao.loginDemo.domain.User;
import club.yhqiao.loginDemo.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public List<User> findAll() {

        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));

        return users;
    }

    @Override
    public Admin FindAdminByUsernameAndPassword(String username, String password) {
        try {
            String sql = "select * from admin where username = ? and password = ?";
            Admin admin = template.queryForObject(sql,
                    new BeanPropertyRowMapper<Admin>(Admin.class),
                    username,
                    password);
            return admin;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addUser(User user) {
        String sql = "insert into user values(null,?,?,?,?,?,?)";
        template.update(sql,
                user.getName(),
                user.getGender(),
                user.getAge(),
                user.getAddress(),
                user.getQq(),
                user.getEmail());
    }

    @Override
    public void delUserById(int id) {
        String sql = "delete from user where id = ?";
        template.update(sql,id);
    }

    @Override
    public User findUserById(int parseInt) {
        String sql = "select * from user where id = ?";
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),parseInt);
        return user;
    }

    @Override
    public void updateUser(User user) {
        String sql = "update user set name = ?,gender = ?,age = ?,address = ?,qq = ?,email = ? where id = ?";
        template.update(sql,
                user.getName(),
                user.getGender(),
                user.getAge(),
                user.getAddress(),
                user.getQq(),
                user.getEmail(),
                user.getId());
    }

    @Override
    public int findTotalCount() {
        String sql = "select count(*) from user";
        return template.queryForObject(sql,Integer.class);
    }

    @Override
    public List<User> findByPage(int start, int rows) {
        String sql = "select * from user limit ?,?";
        return template.query(sql,new BeanPropertyRowMapper<User>(User.class),start,rows);
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {

        String sql = "select count(*) from user where 1 = 1";
        StringBuilder sb = new StringBuilder(sql);
        Set<String> keySet = condition.keySet();

        List<Object> params = new ArrayList<Object>();

        for(String key :keySet) {
            if("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            String value = condition.get(key)[0];
            if(value != null && !"".equals(value)) {
                sb.append(" and "+key +" like ? ");
                params.add("%"+value+"%");
            }
        }
        System.out.println(sb.toString());
        System.out.println(params);
        return template.queryForObject(sb.toString(),Integer.class,params.toArray());
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from user where 1 = 1";

        StringBuilder sb = new StringBuilder(sql);
        Set<String> keySet = condition.keySet();

        List<Object> params = new ArrayList<Object>();

        for(String key :keySet) {
            if("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            String value = condition.get(key)[0];
            if(value != null && !"".equals(value)) {
                sb.append(" and "+key +" like ? ");
                params.add("%"+value+"%");
            }
        }

        sb.append(" order by id desc limit ?,?");
        params.add(start);
        params.add(rows);

        System.out.println(params);

        return template.query(sb.toString(),new BeanPropertyRowMapper<>(User.class),params.toArray());

    }

    @Override
    public void resigtAdmin(Admin admin) {
        String username = admin.getUsername();
        String password = admin.getPassword();
        String sql = "insert into admin values(?,?)";
        template.update(sql,username,password);
    }
}
