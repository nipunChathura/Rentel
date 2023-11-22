package rental.lk.ijse.layered.dao.custom.impl;

import rental.lk.ijse.layered.dao.custom.UserDAO;
import rental.lk.ijse.layered.entity.User;
import rental.lk.ijse.layered.util.CrudUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean add(User entity) throws ClassNotFoundException, SQLException, IOException {
        return CrudUtil.executeUpdate("INSERT INTO user VALUE (?,?,?,?)",
                entity.getUserId(), entity.getUsername(), entity.getPassword(), entity.getStatus());
    }

    @Override
    public boolean delete(String s) throws ClassNotFoundException, SQLException, IOException {
        return false;
    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException, IOException {
        return false;
    }

    @Override
    public User getById(String id) throws SQLException, ClassNotFoundException, IOException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM user WHERE user_id = ?", id);
        if (resultSet.next()) {
            return getUserList(resultSet).get(0);
        }
        return null;
    }

    @Override
    public ArrayList<User> searchUser(String searchValue) throws SQLException, ClassNotFoundException, IOException {
        return null;
    }

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException, IOException {
        return null;
    }

    @Override
    public int getUserLastId() throws Exception {
        return 0;
    }

    @Override
    public User getUserByUsername(String username) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM user WHERE username = ?", username);
        System.out.println("resultSet = " + resultSet.toString());
        if (resultSet.next()) {
            int userId = resultSet.getInt(1);
            String usernameUser = resultSet.getString(2);
            String password = resultSet.getString(3);
            String status = resultSet.getString(4);
            return new User(userId, usernameUser, password, status);
        }
        return null;
    }

    private List<User> getUserList(ResultSet resultSet) throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        while (resultSet.next()) {
            int userId = resultSet.getInt(1);
            String usernameUser = resultSet.getString(2);
            String password = resultSet.getString(3);
            String status = resultSet.getString(4);

            User user = new User(userId, usernameUser, password, status);
            users.add(user);
        }
        System.out.println("users.toString() = " + users.toString());
        return users;
    }
}
