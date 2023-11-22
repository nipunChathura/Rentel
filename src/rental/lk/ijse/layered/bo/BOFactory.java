package rental.lk.ijse.layered.bo;

import rental.lk.ijse.layered.bo.custom.impl.*;


public class BOFactory {

    private static BOFactory boFactory;

    public BOFactory() {
    }

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BOFactoryType factoryType) {
        switch (factoryType) {
            case CATEGORY:
                return new CategoryBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case ODER:
                return new OrderBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
