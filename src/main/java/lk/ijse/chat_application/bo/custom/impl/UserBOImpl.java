package lk.ijse.chat_application.bo.custom.impl;

import lk.ijse.chat_application.bo.custom.UserBO;
import lk.ijse.chat_application.dao.custom.UserDAO;
import lk.ijse.chat_application.dao.custom.impl.UserDAOImpl;
import lk.ijse.chat_application.dto.UserDTO;
import lk.ijse.chat_application.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean addUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
       return userDAO.add(new User(userDTO.getName(),userDTO.getPassword()));
    }

    @Override
    public boolean isExistUser(String user_name) throws SQLException, ClassNotFoundException {
        return userDAO.isExist(user_name);
    }

    @Override
    public boolean chekValidUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return userDAO.chekValid(new User(userDTO.getName(),userDTO.getPassword()));
    }

    @Override
    public List<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException {
        List<User> allUsers = userDAO.getAll();
       List<UserDTO> userDTOS = new ArrayList<>();
       for (User user : allUsers){
           userDTOS.add(new UserDTO(user.getName(),user.getPassword()));
       }
       return userDTOS;
    }
}
