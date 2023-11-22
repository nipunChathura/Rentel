package rental.lk.ijse.layered.entity;

import java.math.BigDecimal;

public class Item implements SuperEntity {
    private int itemId;
    private String name;
    private BigDecimal preDayPrice;
    private BigDecimal extraDayPrice;
    private int categoryId;
    private String status;

    public Item() {
    }

    public Item(int itemId, String name, BigDecimal preDayPrice, BigDecimal extraDayPrice, int categoryId, String status) {
        this.itemId = itemId;
        this.name = name;
        this.preDayPrice = preDayPrice;
        this.extraDayPrice = extraDayPrice;
        this.categoryId = categoryId;
        this.status = status;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPreDayPrice() {
        return preDayPrice;
    }

    public void setPreDayPrice(BigDecimal preDayPrice) {
        this.preDayPrice = preDayPrice;
    }

    public BigDecimal getExtraDayPrice() {
        return extraDayPrice;
    }

    public void setExtraDayPrice(BigDecimal extraDayPrice) {
        this.extraDayPrice = extraDayPrice;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", preDayPrice=" + preDayPrice +
                ", extraDayPrice=" + extraDayPrice +
                ", categoryId=" + categoryId +
                ", status='" + status + '\'' +
                '}';
    }
}
