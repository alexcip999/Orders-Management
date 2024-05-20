package presentation.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ViewProducts extends JFrame{
    private JPanel mainPanel;
    private JButton viewProductsButton;
    private JButton editProductButton;
    private JButton deleteProductButton;
    private JButton addProductButton;
    private JTable tableProducts;

    public ViewProducts(){
        super("Products Management");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(500, 500);
        this.setVisible(true);
    }

    public void addProductButtonListener(ActionListener listener){
        addProductButton.addActionListener(listener);
    }
    public void editProductButtonListener(ActionListener listener){
        editProductButton.addActionListener(listener);
    }
    public void deleteProductButtonListener(ActionListener listener){
        deleteProductButton.addActionListener(listener);
    }
    public void viewProductButtonListener(ActionListener listener){
        viewProductsButton.addActionListener(listener);
    }

    public JButton getViewProductsButton() {
        return viewProductsButton;
    }

    public void setViewProductsButton(JButton viewProductsButton) {
        this.viewProductsButton = viewProductsButton;
    }

    public JButton getEditProductButton() {
        return editProductButton;
    }

    public void setEditProductButton(JButton editProductButton) {
        this.editProductButton = editProductButton;
    }

    public JButton getDeleteProductButton() {
        return deleteProductButton;
    }

    public void setDeleteProductButton(JButton deleteProductButton) {
        this.deleteProductButton = deleteProductButton;
    }

    public JButton getAddProductButton() {
        return addProductButton;
    }

    public void setAddProductButton(JButton addProductButton) {
        this.addProductButton = addProductButton;
    }

    public JTable getTableProducts() {
        return tableProducts;
    }

    public void setTableProducts(JTable tableProducts) {
        this.tableProducts = tableProducts;
    }
}
