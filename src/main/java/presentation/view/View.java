package presentation.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class View extends JFrame{

    private JPanel mainPanel;
    private JButton ordersButton;
    private JButton productsButton;
    private JButton clientsButton;

    public View(){
        super("Orders Management");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
        this.setVisible(true);
    }

    public void clientsButtonListener(ActionListener listener){
        clientsButton.addActionListener(listener);
    }
    public void productsButtonListener(ActionListener listener){
        productsButton.addActionListener(listener);
    }
    public void ordersButtonListener(ActionListener listener){
        ordersButton.addActionListener(listener);
    }

    public JButton getOrdersButton() {
        return ordersButton;
    }

    public void setOrdersButton(JButton ordersButton) {
        this.ordersButton = ordersButton;
    }

    public JButton getProductsButton() {
        return productsButton;
    }

    public void setProductsButton(JButton productsButton) {
        this.productsButton = productsButton;
    }

    public JButton getClientsButton() {
        return clientsButton;
    }

    public void setClientsButton(JButton clientsButton) {
        this.clientsButton = clientsButton;
    }
}
