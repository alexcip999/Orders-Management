package presentation.controllers;

import presentation.view.View;
import presentation.view.ViewClients;
import presentation.view.ViewOrders;
import presentation.view.ViewProducts;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private final View view;

    public Controller(View view) {
        this.view = view;
        this.view.clientsButtonListener(this);
        this.view.productsButtonListener(this);
        this.view.ordersButtonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Clients" :
                ViewClients viewClients = new ViewClients();
                ClientsController clientsController = new ClientsController(viewClients);
                break;
            case "Products" :
                ViewProducts viewProducts = new ViewProducts();
                ProductsController productsController = new ProductsController(viewProducts);
                break;
            case "Orders" :
                ViewOrders viewOrders = new ViewOrders();
                OrdersController ordersController = new OrdersController(viewOrders);
                break;
        }
    }

    public static void main(String[] args) {
        View view1 = new View();
        Controller controller = new Controller(view1);
    }

}
