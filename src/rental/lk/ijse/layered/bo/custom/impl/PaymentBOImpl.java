package rental.lk.ijse.layered.bo.custom.impl;

import rental.lk.ijse.layered.bo.custom.PaymentBO;
import rental.lk.ijse.layered.dto.PaymentDTO;

import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    @Override
    public boolean savePayment(PaymentDTO paymentDTO) {
        return false;
    }

    @Override
    public boolean updatePayment(PaymentDTO paymentDTO) {
        return false;
    }

    @Override
    public boolean deletePayment(int paymentId) {
        return false;
    }

    @Override
    public PaymentDTO getPaymentById(int paymentId) {
        return null;
    }

    @Override
    public List<PaymentDTO> getAllPayment() {
        return null;
    }

    @Override
    public List<PaymentDTO> searchPayment(String value) {
        return null;
    }

    @Override
    public int getPaymentLastId() {
        return 0;
    }
}
