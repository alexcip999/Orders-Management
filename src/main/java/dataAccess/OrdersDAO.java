package dataAccess;

import model.Order;

import javax.swing.table.DefaultTableModel;
import java.util.logging.Logger;

public class OrdersDAO extends AbstractDAO<Order> {
    protected static final Logger LOGGER = Logger.getLogger(OrdersDAO.class.getName());

    public OrdersDAO() {
        super(Order.class);
    }
    public Order insert(Order order){
        return super.insert(order, "orders");
    }
    public DefaultTableModel diplay(){
        return super.display("orders");
    }
}
