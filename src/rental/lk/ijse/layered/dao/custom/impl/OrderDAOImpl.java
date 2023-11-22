package rental.lk.ijse.layered.dao.custom.impl;

import rental.lk.ijse.layered.dao.custom.OrderDAO;
import rental.lk.ijse.layered.entity.Order;
import rental.lk.ijse.layered.util.CrudUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean add(Order entity) throws ClassNotFoundException, SQLException, IOException {
        return CrudUtil.executeUpdate("INSERT INTO orders VALUE (?,?,?,?,?,?,?,?,?)",
                entity.getOrderId(), entity.getOrderDate(), entity.getCustomerName(), entity.getCustomerAddress(),
                entity.getCustomerPhoneNumber(), entity.getCustomerNic(), entity.getTotalAmount(), entity.getTotalExtraAmount(),
                entity.getStatus());
    }

    @Override
    public boolean delete(Integer orderId) throws ClassNotFoundException, SQLException, IOException {
        return CrudUtil.executeUpdate("UPDATE orders SET status = 'DELETED' WHERE order_id = ?", orderId);
    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException, IOException {
        return CrudUtil.executeUpdate("UPDATE orders SET order_date = ?, customer_name = ?, customer_address = ?, customer_phone_number = ?, customer_nic = ?, total_amount = ?, total_extra_amount = ?, status = ? WHERE order_id = ?",
                entity.getOrderDate(), entity.getCustomerName(), entity.getCustomerAddress(),
                entity.getCustomerPhoneNumber(), entity.getCustomerNic(), entity.getTotalAmount(), entity.getTotalExtraAmount(),
                entity.getStatus(), entity.getOrderId());
    }

    @Override
    public Order getById(Integer id) throws SQLException, ClassNotFoundException, IOException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM orders WHERE order_id = ?", id);
        if (!resultSet.next()) {
            return getOrderList(resultSet).get(0);
        }
        return null;
    }

    @Override
    public ArrayList<Order> searchOrder(String value, String status, Date startDate, Date endDate) throws SQLException, ClassNotFoundException, IOException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM orders \n" +
                "WHERE status = 'ACTIVE' \n" +
                "  AND (:value is null or CONCAT(order_date, customer_name, customer_phone_number, customer_nic, total_amount, order_id) LIKE LOWER(concat(concat('%', :value), '%')))\n" +
                "    AND (:categoryStatus is null or status = :categoryStatus)\n" +
                "    AND (:startDate is null or order_date >= :startDate)\n" +
                "    AND (:endDate is null or order_date >= :endDate)", value, value, status, status, startDate, startDate, endDate, endDate);
        return getOrderList(resultSet);
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException, IOException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM orders WHERE status not in ('DELETED')");
        return getOrderList(resultSet);
    }

    @Override
    public int getOrderLastId() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1");
        if (resultSet.next()) {
            int lastId = resultSet.getInt(1);
            return lastId+1;
        }
        return 1;
    }

    private ArrayList<Order> getOrderList(ResultSet resultSet) throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        if (resultSet.next()) {
            int orderId = resultSet.getInt(1);
            Date orderDate = resultSet.getDate(2);
            String customerName = resultSet.getString(3);
            String customerAddress = resultSet.getString(4);
            String customerPhoneNumber = resultSet.getString(5);
            String customerNic = resultSet.getString(6);
            double totalAmount = resultSet.getDouble(7);
            double totalExtraAmount = resultSet.getDouble(8);
            String status = resultSet.getString(9);

            Order order = new Order(orderId, orderDate, customerName, customerAddress, customerPhoneNumber, customerNic, totalAmount, totalExtraAmount, status);
            orders.add(order);
        }
        return orders;
    }
}
