package rental.lk.ijse.layered.dao.custom;

import rental.lk.ijse.layered.dao.CrudDAO;
import rental.lk.ijse.layered.entity.Category;
import rental.lk.ijse.layered.entity.Order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public interface OrderDAO extends CrudDAO<Order, Integer> {
    public ArrayList<Order> searchOrder(String value, String status, Date startDate, Date endDate) throws SQLException, ClassNotFoundException, IOException;
    public int getOrderLastId() throws Exception;
}
