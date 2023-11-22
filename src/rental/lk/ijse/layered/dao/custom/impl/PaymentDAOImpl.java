package rental.lk.ijse.layered.dao.custom.impl;

import rental.lk.ijse.layered.dao.custom.PaymentDAO;
import rental.lk.ijse.layered.entity.Payment;
import rental.lk.ijse.layered.util.CrudUtil;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean add(Payment entity) throws ClassNotFoundException, SQLException, IOException {
        return CrudUtil.executeUpdate("INSERT INTO payment VALUE (?,?,?,?,?)",
                entity.getPaymentId(), entity.getOrderId(), entity.getAmount(), entity.getPaymentDate(),
                entity.getStatus());
    }

    @Override
    public boolean delete(Integer integer) throws ClassNotFoundException, SQLException, IOException {
        return CrudUtil.executeUpdate("UPDATE payment SET status = 'DELETED' WHERE payment_id = ?", integer);
    }

    @Override
    public boolean update(Payment entity) throws SQLException, ClassNotFoundException, IOException {
        return CrudUtil.executeUpdate("UPDATE payment SET order_id = ?, amount = ?, payment_date = ?, status = ? WHERE payment_id = ?",
                entity.getOrderId(), entity.getAmount(), entity.getPaymentDate(), entity.getStatus(),
                entity.getPaymentId());
    }

    @Override
    public Payment getById(Integer id) throws SQLException, ClassNotFoundException, IOException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM payment WHERE payment_id = ?", id);
        if (!resultSet.next()) {
            return getPaymentList(resultSet).get(0);
        }
        return null;
    }

    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException, IOException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM payment WHERE status = 'ACTIVE'");
        if (!resultSet.next()) {
            return getPaymentList(resultSet);
        }
        return null;
    }

    @Override
    public ArrayList<Payment> searchPayment(String searchValue) throws SQLException, ClassNotFoundException, IOException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM payment WHERE status = 'ACTIVE' AND CONCAT(order_id, amount, payment_date, status, payment_id) LIKE %?%");
        if (!resultSet.next()) {
            return getPaymentList(resultSet);
        }
        return null;
    }

    @Override
    public int getPaymentLastId() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT payment_id FROM payment ORDER BY payment_id DESC LIMIT 1");
        if (resultSet.next()) {
            int lastId = resultSet.getInt(1);
            return lastId+1;
        }
        return 1;
    }

    @Override
    public ArrayList<Payment> getAllPaymentByOrderId(int orderOd) throws SQLException, IOException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM payment WHERE status = 'ACTIVE' AND order_id = ?");
        if (!resultSet.next()) {
            return getPaymentList(resultSet);
        }
        return null;
    }

    private ArrayList<Payment> getPaymentList(ResultSet resultSet) throws SQLException {
        ArrayList<Payment> payments = new ArrayList<>();
        if (resultSet.next()) {
            int paymentId = resultSet.getInt(1);
            int orderId = resultSet.getInt(2);
            double amount = resultSet.getDouble(3);
            Date paymentDate = resultSet.getDate(4);
            String status = resultSet.getString(5);

            Payment payment = new Payment(paymentId, orderId, amount, paymentDate, status);
            payments.add(payment);
        }
        return payments;
    }
}
