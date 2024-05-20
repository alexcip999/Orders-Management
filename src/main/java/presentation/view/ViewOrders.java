package presentation.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ViewOrders extends JFrame{
    private JPanel mainPanel;
    private JButton buyButton;
    private JTable clientsTable;
    private JTable productsTable;
    private JTable billTable;
    private JButton viewButton;

    public ViewOrders(){
        super("Orders Management");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(500, 500);
        this.setVisible(true);
    }

    public void buyOrderButtonListener(ActionListener listener){
        buyButton.addActionListener(listener);
    }
    public void viewOrderButtonListener(ActionListener listener){
        viewButton.addActionListener(listener);
    }

    public JButton getBuyButton() {
        return buyButton;
    }

    public void setBuyButton(JButton buyButton) {
        this.buyButton = buyButton;
    }

    public JTable getClientsTable() {
        return clientsTable;
    }

    public void setClientsTable(JTable clientsTable) {
        this.clientsTable = clientsTable;
    }

    public JTable getProductsTable() {
        return productsTable;
    }

    public void setProductsTable(JTable productsTable) {
        this.productsTable = productsTable;
    }

    public JTable getBillTable() {
        return billTable;
    }

    public void setBillTable(JTable billTable) {
        this.billTable = billTable;
    }
}
