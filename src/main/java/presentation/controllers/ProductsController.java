package presentation.controllers;

import businessLogic.ProductBLL;
import dataAccess.ClientDAO;
import dataAccess.ProductDAO;
import model.Client;
import model.Product;
import presentation.view.ViewProducts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductsController implements ActionListener {
    private final ViewProducts viewProducts;
    public DefaultTableModel tableModel;
    public int selectedRowIndex = 0;
    public ProductBLL productBLL = new ProductBLL();

    public ProductsController(ViewProducts viewProducts) {
        this.viewProducts = viewProducts;
        this.viewProducts.addProductButtonListener(this);
        this.viewProducts.editProductButtonListener(this);
        this.viewProducts.deleteProductButtonListener(this);
        this.viewProducts.viewProductButtonListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "add product" :
                JTextField nameField = new JTextField();
                JTextField priceField = new JTextField();
                JTextField quantityField = new JTextField();
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.add(new JLabel("Name Product"));
                panel.add(nameField);
                panel.add(Box.createVerticalStrut(10));
                panel.add(new JLabel("Price "));
                panel.add(priceField);
                panel.add(Box.createVerticalStrut(10));
                panel.add(new JLabel("Quantity "));
                panel.add(quantityField);
                int result = JOptionPane.showConfirmDialog(null, panel, "Enter Values",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    String name = nameField.getText();
                    Double price = Double.parseDouble(priceField.getText());
                    int quantity = Integer.parseInt(quantityField.getText());
                    Product product = new Product(name, price, quantity);
                    productBLL.getProductDAO().insert(product);
                    tableModel = productBLL.getProductDAO().display();
                    viewProducts.getTableProducts().setModel(tableModel);
                }
                break;
            case "view products" :
                tableModel = productBLL.getProductDAO().display();
                viewProducts.getTableProducts().setModel(tableModel);
                break;
            case "delete product" :
                int id = 0;
                selectedRowIndex = viewProducts.getTableProducts().getSelectedRow();
                if (selectedRowIndex != -1) {
                    Object[] rowData = new Object[viewProducts.getTableProducts().getColumnCount()];
                    for (int i = 0; i < viewProducts.getTableProducts().getColumnCount(); i++) {
                        rowData[i] = viewProducts.getTableProducts().getValueAt(selectedRowIndex, i);
                    }
                    if (rowData[0] instanceof Long) {
                        id = ((Long) rowData[0]).intValue();
                    } else if (rowData[0] instanceof Integer) {
                        id = (int) rowData[0];
                    }
                    productBLL.getProductDAO().delete(id);
                } else {
                    JOptionPane.showMessageDialog(null, "No product selected.");
                }
                tableModel = productBLL.getProductDAO().display();
                viewProducts.getTableProducts().setModel(tableModel);
                break;
            case "edit product" :
                JTextField nameEditField = new JTextField();
                JTextField priceEditField = new JTextField();
                JTextField quantityEditField = new JTextField();
                JPanel editPanel = new JPanel();
                selectedRowIndex = viewProducts.getTableProducts().getSelectedRow();
                if (selectedRowIndex != -1) {
                    Object[] rowData = new Object[viewProducts.getTableProducts().getColumnCount()];
                    for (int i = 0; i < viewProducts.getTableProducts().getColumnCount(); i++) {
                        rowData[i] = viewProducts.getTableProducts().getValueAt(selectedRowIndex, i);
                    }
                    int idProduct = (int) rowData[0];
                    nameEditField.setText((String) rowData[1]);
                    priceEditField.setText(String.valueOf(rowData[2]));
                    quantityEditField.setText(String.valueOf(rowData[3]));

                    editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));
                    editPanel.add(new JLabel("Name Product"));
                    editPanel.add(nameEditField);
                    editPanel.add(Box.createVerticalStrut(10));
                    editPanel.add(new JLabel("Price "));
                    editPanel.add(priceEditField);
                    editPanel.add(Box.createVerticalStrut(10));
                    editPanel.add(new JLabel("Quantity "));
                    editPanel.add(quantityEditField);
                    int editResult = JOptionPane.showConfirmDialog(null, editPanel, "Enter Values",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (editResult == JOptionPane.OK_OPTION) {
                        String name = nameEditField.getText();
                        Double price = Double.parseDouble(priceEditField.getText());
                        int quantity = Integer.parseInt(quantityEditField.getText());
                        Product product = new Product(idProduct, name, price, quantity);
                        productBLL.getProductDAO().edit(product);
                    }
                    tableModel = productBLL.getProductDAO().display();
                    viewProducts.getTableProducts().setModel(tableModel);
                }else {
                    JOptionPane.showMessageDialog(null, "No product selected.");
                }
                break;
        }
    }
}
