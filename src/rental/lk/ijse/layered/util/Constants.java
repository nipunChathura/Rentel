package rental.lk.ijse.layered.util;

public class Constants {

    //Application Properties
    public final static String PROPERTY_FILE_NAME = "config. properties";
    public final static String DIVER_CLASS = "db.connection.driver_class";
    public final static String DB_URL = "db.connection.url";
    public final static String DB_USERNAME = "db.connection.username";
    public final static String DB_PASSWORD = "db.connection.password";

    //Factory Type
    public final static String CATEGORY_TYPE = "CATEGORY";
    public final static String ITEM_TYPE = "ITEM";
    public final static String ODER_TYPE = "ODER";
    public final static String ODER_DETAIL_TYPE = "ORDER_DETAIL";
    public final static String PAYMENT_TYPE = "PAYMENT";

    //Common Status
    public final static String ALL_STATUS = "ALL";
    public final static String ACTIVE_STATUS = "ACTIVE";
    public final static String INACTIVE_STATUS = "INACTIVE";
    public final static String DELETED_STATUS = "DELETED";

    //Item Status
    public final static String RESERVED = "RESERVED";
    public final static String DAMAGE = "DAMAGE";
    public final static String FREE = "FREE";

    //Order Status
    public final static String NON_PAID_STATUS = "NON_PAID";
    public final static String PAID_STATUS = "PAID";
    public final static String PAID_COMPLETE_STATUS = "PAID_COMPLETE";

    //Order Detail Status
    public final static String DELIVERED_STATUS = "DELIVERED";
    public final static String NOT_DELIVERED_STATUS = "NOT_DELIVERED";

    //Alert Type
    public final static String ALERT_SUCCESS = "SUCCESS";
    public final static String ALERT_ERROR = "ERROR";
    public final static String ALERT_WARNING = "WARNING";

    //Action Control Type
    public final static String ADD_NEW_ACTION = "ADD_NEW";
    public final static String MODIFY_ACTION = "MODIFY";
}
