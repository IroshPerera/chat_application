package lk.ijse.chat_application.dao.custom;

import lk.ijse.chat_application.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    boolean add(User user) throws SQLException, ClassNotFoundException;
    boolean isExist(String user_name) throws SQLException, ClassNotFoundException;
    boolean chekValid(User user) throws SQLException, ClassNotFoundException;
    List<User> getAll() throws SQLException, ClassNotFoundException;

}
