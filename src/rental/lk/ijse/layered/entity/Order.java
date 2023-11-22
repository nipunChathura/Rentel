package rental.lk.ijse.layered.entity;

import java.util.Date;

public class Order implements SuperEntity {
    private int orderId;
    private Date orderDate;
    private String customerName;
    private String customerAddress;
    private String customerPhoneNumber;
    private String customerNic;
    private double totalAmount;
    private double totalExtraAmount;
    private String status;

    public Order() {
    }

    public Order(int orderId, Date orderDate, String customerName, String customerAddress, String customerPhoneNumber, String customerNic, double totalAmount, double totalExtraAmount, String status) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerNic = customerNic;
        this.totalAmount = totalAmount;
        this.totalExtraAmount = totalExtraAmount;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
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

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerPhoneNumber='" + customerPhoneNumber + '\'' +
                ", customerNic='" + customerNic + '\'' +
                ", totalAmount=" + totalAmount +
                ", totalExtraAmount=" + totalExtraAmount +
                ", status='" + status + '\'' +
                '}';
    }
}
