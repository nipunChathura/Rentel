package rental.lk.ijse.layered.bo.custom.impl;

import rental.lk.ijse.layered.bo.custom.ItemBO;
import rental.lk.ijse.layered.bo.custom.OrderBO;
import rental.lk.ijse.layered.dao.DAOFactory;
import rental.lk.ijse.layered.dao.DAOFactoryType;
import rental.lk.ijse.layered.dao.custom.CategoryDAO;
import rental.lk.ijse.layered.dao.custom.ItemDAO;
import rental.lk.ijse.layered.dao.custom.OrderDAO;
import rental.lk.ijse.layered.dao.custom.OrderDetailsDAO;
import rental.lk.ijse.layered.db.DBConnection;
import rental.lk.ijse.layered.dto.OrderDTO;
import rental.lk.ijse.layered.dto.OrderDetailsDTO;
import rental.lk.ijse.layered.entity.Order;
import rental.lk.ijse.layered.entity.OrderDetails;
import rental.lk.ijse.layered.util.Utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderBOImpl implements OrderBO {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactoryType.ODER);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getInstance().getDAO(DAOFactoryType.ORDER_DETAIL);
    ItemDAO itemBO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactoryType.ITEM);

    @Override
    public boolean saveOrder(OrderDTO orderDTO) throws SQLException {
        List<OrderDetailsDTO> detailsDTO = orderDTO.getDetailsDTO();
        Connection connection = null;

        boolean isOrderAdded = false;

        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            Order order = orderDTOToOrder(orderDTO);
            boolean add = orderDAO.add(order);
            if (add) {
                if (!detailsDTO.isEmpty()) {
                    for (int i = 0; i < detailsDTO.size(); i++) {
                        OrderDetails orderDetails = orderDetailsDTOToOrderDetails(detailsDTO.get(i));
                        int orderDetailLastId = orderDetailsDAO.getOrderDetailLastId();
                        orderDetails.setOrderDetailId(orderDetailLastId);
                        int errorCount = 0;
                        try {
                            boolean detailsAdd = orderDetailsDAO.add(orderDetails);
                            if (!detailsAdd) {
                                errorCount +=1;
                            }
                        } catch (Exception e) {
                            errorCount +=1;
                        }

                        if (errorCount == 0) {
                            connection.commit();
                            isOrderAdded = true;
                        } else {
                            connection.rollback();
                        }
                    }
                }
            } else {
                connection.rollback();
            }

        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        }
        return isOrderAdded;
    }

    @Override
    public boolean updateOrder(OrderDTO orderDTO) throws SQLException {

        List<OrderDetailsDTO> detailsDTO = orderDTO.getDetailsDTO();
        Connection connection = null;
        boolean isOrderUpdated = false;

        try {
            connection = DBConnection.getInstance().getConnection();

            connection.setAutoCommit(false);
            boolean isUpdated = false;
            isUpdated = orderDAO.update(orderDTOToOrder(orderDTO));

            if (!detailsDTO.isEmpty()) {
                int errorCount = 0;
                if (isUpdated) {
                    for (int i = 0; i < detailsDTO.size(); i++) {
                        OrderDetails orderDetails = orderDetailsDTOToOrderDetails(detailsDTO.get(i));
                        try {
                            boolean update = orderDetailsDAO.update(orderDetails);
                            if (!update) {
                                errorCount +=1;
                            }
                        } catch (Exception e) {
                            errorCount += 1;
                        }
                    }

                    if (errorCount == 0) {
                        connection.commit();
                        isOrderUpdated = true;
                    }
                } else {
                    connection.rollback();
                }
            }
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
        }

        return isOrderUpdated;
    }

    @Override
    public boolean deleteOrder(int orderId) {
        Connection connection = null;
        boolean isOrderDeleted = false;
        try {
            connection = DBConnection.getInstance().getConnection();

            connection.setAutoCommit(false);
            boolean isDelete = false;
            Order order = orderDAO.getById(orderId);
            if (order != null) {
                 isDelete = orderDAO.delete(orderId);
            } else {
                System.out.println("Order is not exists");
            }

            ArrayList<OrderDetails> allDetailsByOrderId = orderDetailsDAO.getAllDetailsByOrderId(orderId);
            if (allDetailsByOrderId != null) {
                int errorCount = 0;
                if (isDelete) {
                    for (int i = 0; i < allDetailsByOrderId.size(); i++) {
                        try {
                            boolean delete = orderDetailsDAO.delete(allDetailsByOrderId.indexOf(i));
                            if (!delete) {
                                errorCount +=1;
                            }
                        } catch (Exception e) {
                            errorCount +=1;
                        }
                    }
                    if (errorCount == 0) {
                        isOrderDeleted = true;
                        connection.commit();
                    } else {
                        connection.rollback();
                    }
                } else {
                    connection.rollback();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isOrderDeleted;
    }

    @Override
    public OrderDTO getOrderById(int orderId) throws SQLException, IOException, ClassNotFoundException {
        Order byId = orderDAO.getById(orderId);
        return orderToOrderDTO(byId);
    }

    @Override
    public List<OrderDTO> getAllOrder() throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Order> allOrders = orderDAO.getAll();
        ArrayList<OrderDTO> orderDTOS = new ArrayList<>();
        if (allOrders != null) {
            allOrders.forEach( order -> {
                OrderDTO orderDTO = orderToOrderDTO(order);
                orderDTOS.add(orderDTO);
            });
        }
        return orderDTOS;
    }

    @Override
    public List<OrderDTO> searchOrder(String value, String status, Date startDate, Date endDate) throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Order> orders = orderDAO.searchOrder(value, status, startDate, endDate);
        ArrayList<OrderDTO> orderDTOS = new ArrayList<>();
        if (!orders.isEmpty()) {
            orders.forEach( order -> {
                OrderDTO orderDTO = orderToOrderDTO(order);
                orderDTOS.add(orderDTO);
            });
        }
        return orderDTOS;
    }

    @Override
    public int getOrderLastId() throws Exception {
        return orderDAO.getOrderLastId();
    }

    @Override
    public int getOrderDetailLastId() throws Exception {
        return orderDetailsDAO.getOrderDetailLastId();
    }

    private Order orderDTOToOrder(OrderDTO orderDTO) throws ParseException {
        return new Order(
                orderDTO.getOrderId(),
                Utils.getUtilDate(orderDTO.getOrderDate()),
                orderDTO.getCustomerName(),
                orderDTO.getCustomerAddress(),
                orderDTO.getCustomerPhoneNumber(),
                orderDTO.getCustomerNic(),
                orderDTO.getTotalAmount(),
                orderDTO.getTotalExtraAmount(),
                orderDTO.getStatus()
        );
    }

    private OrderDTO orderToOrderDTO(Order order) {
        return new OrderDTO(
          order.getOrderId(),
          Utils.getStringDateToUtilDate(order.getOrderDate()),
          order.getCustomerName(),
          order.getCustomerAddress(),
          order.getCustomerPhoneNumber(),
          order.getCustomerNic(),
          order.getTotalAmount(),
          order.getTotalExtraAmount(),
          order.getStatus(),
          null
        );
    }

    private OrderDetails orderDetailsDTOToOrderDetails(OrderDetailsDTO orderDetailsDTO) throws ParseException {
        return new OrderDetails(
                orderDetailsDTO.getOrderDetailId(),
                orderDetailsDTO.getOrderId(),
                orderDetailsDTO.getItemId(),
                Utils.getUtilDate(orderDetailsDTO.getOrderReturnDate()),
                Utils.getUtilDate(orderDetailsDTO.getActualOrderReturnDate()),
                orderDetailsDTO.getAmount(),
                orderDetailsDTO.getExtraAmount(),
                orderDetailsDTO.getStatus()
        );
    }

    private OrderDetailsDTO orderDetailsToOrderDetailsDTO(OrderDetails orderDetails) throws SQLException, IOException, ClassNotFoundException {
        return new OrderDetailsDTO(
                orderDetails.getOrderDetailId(),
                orderDetails.getOrderId(),
                itemBO.getById(orderDetails.getItemId()).getName(),
                orderDetails.getItemId(),
                Utils.getStringDateToUtilDate(orderDetails.getOrderReturnDate()),
                Utils.getStringDateToUtilDate(orderDetails.getActualOrderReturnDate()),
                orderDetails.getAmount(),
                orderDetails.getExtraAmount(),
                orderDetails.getStatus()
        );
    }
}
