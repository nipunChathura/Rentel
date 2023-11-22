package rental.lk.ijse.layered.bo.custom;

import rental.lk.ijse.layered.bo.SuperBO;
import rental.lk.ijse.layered.dto.OrderDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface OrderBO extends SuperBO {
    public boolean saveOrder(OrderDTO orderDTO) throws SQLException;
    public boolean updateOrder(OrderDTO orderDTO) throws SQLException;
    public boolean deleteOrder(int orderId);
    public OrderDTO getOrderById(int orderId) throws SQLException, IOException, ClassNotFoundException;
    public List<OrderDTO> getAllOrder() throws SQLException, IOException, ClassNotFoundException;
    public List<OrderDTO> searchOrder(String value, String status, Date startDate, Date endDate) throws SQLException, IOException, ClassNotFoundException;
    public int getOrderLastId() throws Exception;
    public int getOrderDetailLastId() throws Exception;
}
