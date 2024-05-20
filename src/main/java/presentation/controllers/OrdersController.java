package presentation.controllers;

import businessLogic.ClientBLL;
import businessLogic.OrderBLL;
import businessLogic.ProductBLL;
import businessLogic.validators.QuantityValidator;
import businessLogic.validators.StockValidator;
import dataAccess.ClientDAO;
import dataAccess.OrdersDAO;
import dataAccess.ProductDAO;
import model.Client;
import model.Order;
import model.Product;
import presentation.view.ViewOrders;
import presentation.view.ViewProducts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class OrdersController implements ActionListener {
    private final ViewOrders viewOrders;
    public int selectedRowIndexOfClients = 0;
    public int selectedRowIndexOfProducts = 0;
    public DefaultTableModel tableClientsModel;
    public DefaultTableModel tabelProductsModel;
    public DefaultTableModel tabelOrdersModel;
    public ClientBLL clientBLL = new ClientBLL();
    public ProductBLL productBLL = new ProductBLL();
    public OrderBLL orderBLL = new OrderBLL();

    public OrdersController(ViewOrders viewOrders) {
        this.viewOrders = viewOrders;
        this.viewOrders.buyOrderButtonListener(this);
        this.viewOrders.viewOrderButtonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("View")){
            tableClientsModel = clientBLL.getClientDAO().display();
            tabelProductsModel = productBLL.getProductDAO().display();
            viewOrders.getClientsTable().setModel(tableClientsModel);
            viewOrders.getProductsTable().setModel(tabelProductsModel);
        }
        if(e.getActionCommand().equals("Buy")){
            selectedRowIndexOfClients = viewOrders.getClientsTable().getSelectedRow();
            selectedRowIndexOfProducts = viewOrders.getProductsTable().getSelectedRow();
            if(selectedRowIndexOfProducts != -1 && selectedRowIndexOfClients != -1){
                Object[] rowClientData = new Object[viewOrders.getClientsTable().getColumnCount()];
                for(int i = 0; i < viewOrders.getClientsTable().getColumnCount(); i++){
                    rowClientData[i] = viewOrders.getClientsTable().getValueAt(selectedRowIndexOfClients, i);
                }
                Client client = new Client((int)rowClientData[0], (String) rowClientData[1], (String) rowClientData[2], (String)rowClientData[3], (int)rowClientData[4]);
                Object[] rowProductData = new Object[viewOrders.getProductsTable().getColumnCount()];
                for(int i = 0; i < viewOrders.getProductsTable().getColumnCount(); i++){
                    rowProductData[i] = viewOrders.getProductsTable().getValueAt(selectedRowIndexOfProducts, i);
                }
                Product product = new Product((int)rowProductData[0], (String)rowProductData[1], (Double) rowProductData[2], (int)rowProductData[3]);
                LocalDateTime localDateTime = LocalDateTime.now();
                ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
                Instant instant = zonedDateTime.toInstant();
                Date date = Date.from(instant);
                Order order = new Order(client.getName(), product.getNameProduct(), product.getQuantityAvailable(), product.getPrice(), date);
                String quantityInput = JOptionPane.showInputDialog(null, "Enter quantity");
                QuantityValidator quantityValidator = new QuantityValidator(Integer.parseInt(quantityInput), true);
                quantityValidator.validate(order);
                if(quantityValidator.isValid()){
                    order.setQuantity(Integer.parseInt(quantityInput));
                    order.setPriceProduct(Integer.parseInt(quantityInput) * product.getPrice());
                    orderBLL.getOrdersDAO().insert(order);
                    tabelOrdersModel = orderBLL.getOrdersDAO().diplay();
                    viewOrders.getBillTable().setModel(tabelOrdersModel);
                    int quantity  = product.getQuantityAvailable() - Integer.parseInt(quantityInput);
                    product.setQuantityAvailable(quantity);

                    StockValidator stockValidator = new StockValidator(true);
                    stockValidator.validate(product);
                    if(stockValidator.isValid()){
                        productBLL.getProductDAO().edit(product);
                    }else{
                        productBLL.getProductDAO().delete(product.getIdProduct());
                    }

                }
            }else{
                JOptionPane.showMessageDialog(null, "No client or product selected.");
            }
        }
    }
}
