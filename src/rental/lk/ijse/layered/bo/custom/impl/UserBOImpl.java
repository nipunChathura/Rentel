package rental.lk.ijse.layered.bo.custom.impl;

import rental.lk.ijse.layered.bo.custom.UserBO;
import rental.lk.ijse.layered.dao.DAOFactory;
import rental.lk.ijse.layered.dao.DAOFactoryType;
import rental.lk.ijse.layered.dao.custom.UserDAO;
import rental.lk.ijse.layered.dto.UserDTO;
import rental.lk.ijse.layered.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactoryType.USER);

    @Override
    public boolean saveUser(UserDTO userDTO) throws SQLException, IOException, ClassNotFoundException {
        return userDAO.add(userDTOToUser(userDTO));
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws SQLException, IOException, ClassNotFoundException {
        return userDAO.update(userDTOToUser(userDTO));
    }

    @Override
    public boolean deleteUser(int userId) throws SQLException, IOException, ClassNotFoundException {
        return userDAO.delete(String.valueOf(userId));
    }

    @Override
    public UserDTO getUserById(int userId) throws SQLException, IOException, ClassNotFoundException {
        User user = userDAO.getById(String.valueOf(userId));
        return new UserDTO(user.getUserId(), user.getUsername(), user.getPassword(), user.getStatus());
    }

    @Override
    public List<UserDTO> getAllUser() throws SQLException, IOException, ClassNotFoundException {
        ArrayList<User> users = userDAO.getAll();
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        users.forEach(user -> {
            userDTOS.add(userToUserDTO(user));
        });
        return userDTOS;
    }

    @Override
    public List<UserDTO> searchUser(String value) throws SQLException, IOException, ClassNotFoundException {
        ArrayList<User> search = userDAO.searchUser(value);
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        search.forEach(user -> {
            userDTOS.add(userToUserDTO(user));
        });
        return userDTOS;
    }

    @Override
    public int getUserLastId() throws Exception {
        return userDAO.getUserLastId();
    }

    @Override
    public UserDTO getUserByUsername(String username) throws Exception {
        return userToUserDTO(userDAO.getUserByUsername(username));
    }

    private UserDTO userToUserDTO(User user) {
        if (user != null) {
            return new UserDTO(
                    user.getUserId(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getStatus()
            );
        }
        return null;
    }

    private User userDTOToUser(UserDTO userDTO) {
        if (userDTO != null) {
            return new User(
                    userDTO.getUserId(),
                    userDTO.getUsername(),
                    userDTO.getPassword(),
                    userDTO.getStatus()
            );
        }
       return null;
    }
}
