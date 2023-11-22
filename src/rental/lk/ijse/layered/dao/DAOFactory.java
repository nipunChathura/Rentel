package rental.lk.ijse.layered.dao;

import rental.lk.ijse.layered.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    public DAOFactory() {
    }

    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }

        return daoFactory;
    }

    public SuperDAO getDAO(DAOFactoryType types) {
        switch (types) {
            case CATEGORY:
                return new CategoryDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ODER:
                return new OrderDAOImpl();
            case ORDER_DETAIL:
                return new OrderDetailDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case USER:
                return new UserDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }

    }
}
