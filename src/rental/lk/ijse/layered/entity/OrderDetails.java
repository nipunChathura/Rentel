package rental.lk.ijse.layered.entity;

import java.util.Date;

public class OrderDetails implements SuperEntity {
    private int orderDetailId;
    private int orderId;
    private int itemId;
    private Date orderReturnDate;
    private Date actualOrderReturnDate;
    private double amount;
    private double extraAmount;
    private String status;

    public OrderDetails() {
    }

    public OrderDetails(int orderDetailId, int orderId, int itemId, Date orderReturnDate, Date actualOrderReturnDate, double amount, double extraAmount, String status) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.itemId = itemId;
        this.orderReturnDate = orderReturnDate;
        this.actualOrderReturnDate = actualOrderReturnDate;
        this.amount = amount;
        this.extraAmount = extraAmount;
        this.status = status;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Date getOrderReturnDate() {
        return orderReturnDate;
    }

    public void setOrderReturnDate(Date orderReturnDate) {
        this.orderReturnDate = orderReturnDate;
    }

    public Date getActualOrderReturnDate() {
        return actualOrderReturnDate;
    }

    public void setActualOrderReturnDate(Date actualOrderReturnDate) {
        this.actualOrderReturnDate = actualOrderReturnDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getExtraAmount() {
        return extraAmount;
    }

    public void setExtraAmount(double extraAmount) {
        this.extraAmount = extraAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderDetailId=" + orderDetailId +
                ", orderId=" + orderId +
                ", itemId=" + itemId +
                ", orderReturnDate=" + orderReturnDate +
                ", actualOrderReturnDate=" + actualOrderReturnDate +
                ", amount=" + amount +
                ", extraAmount=" + extraAmount +
                ", status='" + status + '\'' +
                '}';
    }
}
