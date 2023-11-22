package rental.lk.ijse.layered.bo.custom;

import rental.lk.ijse.layered.bo.SuperBO;
import rental.lk.ijse.layered.dto.UserDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {
    public boolean saveUser(UserDTO userDTO) throws SQLException, IOException, ClassNotFoundException;
    public boolean updateUser(UserDTO userDTO) throws SQLException, IOException, ClassNotFoundException;
    public boolean deleteUser(int userId) throws SQLException, IOException, ClassNotFoundException;
    public UserDTO getUserById(int userId) throws SQLException, IOException, ClassNotFoundException;
    public List<UserDTO> getAllUser() throws SQLException, IOException, ClassNotFoundException;
    public List<UserDTO> searchUser(String value) throws SQLException, IOException, ClassNotFoundException;
    public int getUserLastId() throws Exception;
    public UserDTO getUserByUsername(String username) throws Exception;
}
