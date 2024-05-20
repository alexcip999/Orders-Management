package businessLogic;

import businessLogic.validators.QuantityValidator;
import businessLogic.validators.Validator;
import dataAccess.OrdersDAO;
import model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderBLL {
    private List<Validator<Order>> validators;
    private OrdersDAO ordersDAO;
    public void ProductBLL(int quantity){
        validators = new ArrayList<Validator<Order>>();
        ordersDAO = new OrdersDAO();
    }

    public OrdersDAO getOrdersDAO() {
        return ordersDAO;
    }
}
