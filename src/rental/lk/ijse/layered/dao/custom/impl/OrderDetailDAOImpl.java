package rental.lk.ijse.layered.dao.custom.impl;

import rental.lk.ijse.layered.dao.custom.OrderDetailsDAO;
import rental.lk.ijse.layered.entity.OrderDetails;
import rental.lk.ijse.layered.util.CrudUtil;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailsDAO {
    @Override
    public boolean add(OrderDetails entity) throws ClassNotFoundException, SQLException, IOException {
        return CrudUtil.executeUpdate("INSERT INTO order_details VALUE (?,?,?,?,?,?,?,?)",
                entity.getOrderDetailId(), entity.getOrderId(), entity.getItemId(), entity.getOrderReturnDate(),
                entity.getOrderReturnDate(), entity.getAmount(), entity.getExtraAmount(), entity.getStatus());
    }

    @Override
    public boolean delete(Integer integer) throws ClassNotFoundException, SQLException, IOException {
        return CrudUtil.executeUpdate("UPDATE order_details SET status = 'DELETED' WHERE order_detail_id = ?", integer);
    }

    @Override
    public boolean update(OrderDetails entity) throws SQLException, ClassNotFoundException, IOException {
        return CrudUtil.executeUpdate("UPDATE order_details SET order_id = ?, item_id = ?, order_return_date = ?, order_actuarial_return_date = ?,amount = ?, extra_amount = ?, status = ? WHERE order_detail_id = ?",
                entity.getOrderId(), entity.getItemId(), entity.getOrderReturnDate(), entity.getOrderReturnDate(),
                entity.getAmount(), entity.getExtraAmount(), entity.getStatus(), entity.getOrderDetailId());
    }

    @Override
    public OrderDetails getById(Integer id) throws SQLException, ClassNotFoundException, IOException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM order_details WHERE order_detail_id = ?", id);
        if (!resultSet.next()) {
            return getDetailsList(resultSet).get(0);
        }
        return null;
    }

    @Override
    public ArrayList<OrderDetails> getAll() throws SQLException, ClassNotFoundException, IOException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM order_details WHERE status = 'ACTIVE'");
        if (!resultSet.next()) {
            return getDetailsList(resultSet);
        }
        return null;
    }

    @Override
    public ArrayList<OrderDetails> searchOrderDetail(String searchValue) throws SQLException, ClassNotFoundException, IOException {
//        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM order_details WHERE status = 'ACTIVE'");
//        if (!resultSet.next()) {
//            return getDetailsList(resultSet);
//        }
        return null;
    }

    @Override
    public int getOrderDetailLastId() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT order_detail_id FROM order_details ORDER BY order_detail_id DESC LIMIT 1");
        if (resultSet.next()) {
            int lastId = resultSet.getInt(1);
            return lastId+1;
        }
        return 1;
    }

    @Override
    public ArrayList<OrderDetails> getAllDetailsByOrderId(int orderId) throws SQLException, IOException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM order_details WHERE status not in ('DELETED') AND order_id = ?");
        if (!resultSet.next()) {
            return getDetailsList(resultSet);
        }
        return null;
    }

    private ArrayList<OrderDetails> getDetailsList(ResultSet resultSet) throws SQLException {
        ArrayList<OrderDetails> orderDetails = new ArrayList<>();
        if (!resultSet.next()) {
            int orderDetailId = resultSet.getInt(1);
            int orderId = resultSet.getInt(2);
            int itemId = resultSet.getInt(3);
            Date returnDate = resultSet.getDate(4);
            Date actualReturnDate = resultSet.getDate(5);
            double amount = resultSet.getDouble(6);
            double extraAmount = resultSet.getDouble(6);
            String status = resultSet.getString(7);

            OrderDetails orderDetails1 = new OrderDetails(
                    orderDetailId, orderId, itemId, returnDate, actualReturnDate, amount, extraAmount, status);
            orderDetails.add(orderDetails1);
        }
        return orderDetails;
    }
}
