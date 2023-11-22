package rental.lk.ijse.layered.dto;

import java.util.Date;

public class OrderDetailsDTO implements SupperDTO {
    private int orderDetailId;
    private int orderId;
    private String itemName;
    private int itemId;
    private String orderReturnDate;
    private String actualOrderReturnDate;
    private double amount;
    private double extraAmount;
    private String status;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(int orderDetailId, int orderId, String itemName, int itemId, String orderReturnDate, String actualOrderReturnDate, double amount, double extraAmount, String status) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.itemName = itemName;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getOrderReturnDate() {
        return orderReturnDate;
    }

    public void setOrderReturnDate(String orderReturnDate) {
        this.orderReturnDate = orderReturnDate;
    }

    public String getActualOrderReturnDate() {
        return actualOrderReturnDate;
    }

    public void setActualOrderReturnDate(String actualOrderReturnDate) {
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
        return "OrderDetailsDTO{" +
                "orderDetailId=" + orderDetailId +
                ", orderId=" + orderId +
                ", itemName='" + itemName + '\'' +
                ", itemId=" + itemId +
                ", orderReturnDate=" + orderReturnDate +
                ", actualOrderReturnDate=" + actualOrderReturnDate +
                ", amount=" + amount +
                ", extraAmount=" + extraAmount +
                ", status='" + status + '\'' +
                '}';
    }
}
