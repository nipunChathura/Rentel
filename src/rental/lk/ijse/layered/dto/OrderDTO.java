package rental.lk.ijse.layered.dto;

import rental.lk.ijse.layered.entity.OrderDetails;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDTO implements SupperDTO {
    private int orderId;
    private String orderDate;
    private String customerName;
    private String customerAddress;
    private String customerPhoneNumber;
    private String customerNic;
    private double totalAmount;
    private double totalExtraAmount;
    private String status;
    private List<OrderDetailsDTO> detailsDTOS;

    public OrderDTO() {
    }

    public OrderDTO(int orderId, String orderDate, String customerName, String customerAddress, String customerPhoneNumber, String customerNic, double totalAmount, double totalExtraAmount, String status, List<OrderDetailsDTO> detailsDTO) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerNic = customerNic;
        this.totalAmount = totalAmount;
        this.totalExtraAmount = totalExtraAmount;
        this.status = status;
        this.detailsDTOS = detailsDTO;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String  getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getCustomerNic() {
        return customerNic;
    }

    public void setCustomerNic(String customerNic) {
        this.customerNic = customerNic;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalExtraAmount() {
        return totalExtraAmount;
    }

    public void setTotalExtraAmount(double totalExtraAmount) {
        this.totalExtraAmount = totalExtraAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderDetailsDTO> getDetailsDTO() {
        return detailsDTOS;
    }

    public void setDetailsDTO(ArrayList<OrderDetailsDTO> detailsDTO) {
        this.detailsDTOS = detailsDTO;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerPhoneNumber='" + customerPhoneNumber + '\'' +
                ", customerNic='" + customerNic + '\'' +
                ", totalAmount=" + totalAmount +
                ", totalExtraAmount=" + totalExtraAmount +
                ", status='" + status + '\'' +
                ", detailsDTO=" + detailsDTOS +
                '}';
    }
}
