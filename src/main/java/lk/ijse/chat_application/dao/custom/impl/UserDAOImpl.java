package lk.ijse.chat_application.dao.custom.impl;

import lk.ijse.chat_application.dao.SQLUtil;
import lk.ijse.chat_application.dao.custom.UserDAO;
import lk.ijse.chat_application.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean add(User user) throws SQLException, ClassNotFoundException {
        String sql ="INSERT INTO user(name,password) VALUES(?,?)";
        return SQLUtil.execute(sql,user.getName(),user.getPassword());
    }

    @Override
    public boolean isExist(String user_name) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM user WHERE name = ?";
        ResultSet resultSet = SQLUtil.execute(sql,user_name);
        return resultSet.next();
    }

    @Override
    public boolean chekValid(User user) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM user WHERE name = ?";
        ResultSet resultSet = SQLUtil.execute(sql,user.getName());
        if (resultSet.next()) {
            String pass = resultSet.getString(2);
            return pass.equals(user.getPassword());

        }
        return false;

    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM user";
        List<User> userList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()){
            userList.add(new User(resultSet.getString(1),resultSet.getString(2)));
        }
        return userList;
    }
}
