package rental.lk.ijse.layered.dao.custom;

import rental.lk.ijse.layered.dao.CrudDAO;
import rental.lk.ijse.layered.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserDAO extends CrudDAO<User, String> {
    public ArrayList<User> searchUser(String searchValue) throws SQLException, ClassNotFoundException, IOException;
    public int getUserLastId() throws Exception;
    public User getUserByUsername(String username) throws Exception;
}
