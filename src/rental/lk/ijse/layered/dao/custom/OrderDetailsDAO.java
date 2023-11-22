package rental.lk.ijse.layered.dao.custom;

import rental.lk.ijse.layered.dao.CrudDAO;
import rental.lk.ijse.layered.entity.Category;
import rental.lk.ijse.layered.entity.OrderDetails;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsDAO extends CrudDAO<OrderDetails, Integer> {
    public ArrayList<OrderDetails> searchOrderDetail(String searchValue) throws SQLException, ClassNotFoundException, IOException;
    public int getOrderDetailLastId() throws Exception;
    public ArrayList<OrderDetails> getAllDetailsByOrderId(int orderId) throws SQLException, IOException, ClassNotFoundException;
}
