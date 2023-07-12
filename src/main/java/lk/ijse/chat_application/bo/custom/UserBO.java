package lk.ijse.chat_application.bo.custom;

import lk.ijse.chat_application.dto.UserDTO;

import java.sql.SQLException;
import java.util.List;

public interface UserBO {
    boolean addUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;
    boolean isExistUser(String user_name) throws SQLException, ClassNotFoundException;
    boolean chekValidUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;
    List<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException;
}
