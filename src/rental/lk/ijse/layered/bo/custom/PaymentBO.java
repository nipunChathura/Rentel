package rental.lk.ijse.layered.bo.custom;

import rental.lk.ijse.layered.bo.SuperBO;
import rental.lk.ijse.layered.dto.PaymentDTO;

import java.util.List;

public interface PaymentBO extends SuperBO {
    public boolean savePayment(PaymentDTO paymentDTO);
    public boolean updatePayment(PaymentDTO paymentDTO);
    public boolean deletePayment(int paymentId);
    public PaymentDTO getPaymentById(int paymentId);
    public List<PaymentDTO> getAllPayment();
    public List<PaymentDTO> searchPayment(String value);
    public int getPaymentLastId();
}
