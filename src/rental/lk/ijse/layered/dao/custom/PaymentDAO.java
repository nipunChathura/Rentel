package rental.lk.ijse.layered.dao.custom;

import rental.lk.ijse.layered.dao.CrudDAO;
import rental.lk.ijse.layered.entity.Category;
import rental.lk.ijse.layered.entity.Payment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentDAO extends CrudDAO<Payment, Integer> {
    public ArrayList<Payment> searchPayment(String searchValue) throws SQLException, ClassNotFoundException, IOException;
    public int getPaymentLastId() throws Exception;
    public ArrayList<Payment> getAllPaymentByOrderId(int orderOd) throws SQLException, IOException, ClassNotFoundException;
}
