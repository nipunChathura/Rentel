package rental.lk.ijse.layered.dto;

public class ItemDTO implements SupperDTO {
    private int itemId;
    private String name;
    private double preDayPrice;
    private double extraDayPrice;
    private String categoryName;
    private int categoryId;
    private String status;

    public ItemDTO() {
    }

    public ItemDTO(int itemId, String name, double preDayPrice, double extraDayPrice, String categoryName, int categoryId, String status) {
        this.itemId = itemId;
        this.name = name;
        this.preDayPrice = preDayPrice;
        this.extraDayPrice = extraDayPrice;
        this.categoryName = categoryName;
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

    public double getPreDayPrice() {
        return preDayPrice;
    }

    public void setPreDayPrice(double preDayPrice) {
        this.preDayPrice = preDayPrice;
    }

    public double getExtraDayPrice() {
        return extraDayPrice;
    }

    public void setExtraDayPrice(double extraDayPrice) {
        this.extraDayPrice = extraDayPrice;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
        return "ItemDTO{" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", preDayPrice=" + preDayPrice +
                ", extraDayPrice=" + extraDayPrice +
                ", categoryName='" + categoryName + '\'' +
                ", categoryId=" + categoryId +
                ", status='" + status + '\'' +
                '}';
    }
}
