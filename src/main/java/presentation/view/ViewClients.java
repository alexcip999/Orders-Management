package presentation.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ViewClients extends JFrame{
    private JPanel mainPanel;
    private JButton viewClientsButton;
    private JButton editClientButton;
    private JButton deleteClientButton;
    private JButton addClientButton;
    private JTable tableClients;

    public ViewClients(){
        super("Clients Management");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(500, 500);
        this.setVisible(true);
    }

    public void addClientButtonListener(ActionListener listener){
        addClientButton.addActionListener(listener);
    }
    public void editClientButtonListener(ActionListener listener){
        editClientButton.addActionListener(listener);
    }
    public void deleteClientButtonListener(ActionListener listener){
        deleteClientButton.addActionListener(listener);
    }
    public void viewClientsButtonListener(ActionListener listener){
        viewClientsButton.addActionListener(listener);
    }

    public JButton getViewClientsButton() {
        return viewClientsButton;
    }

    public void setViewClientsButton(JButton viewClientsButton) {
        this.viewClientsButton = viewClientsButton;
    }

    public JButton getEditClientButton() {
        return editClientButton;
    }

    public void setEditClientButton(JButton editClientButton) {
        this.editClientButton = editClientButton;
    }

    public JButton getDeleteClientButton() {
        return deleteClientButton;
    }

    public void setDeleteClientButton(JButton deleteClientButton) {
        this.deleteClientButton = deleteClientButton;
    }

    public JButton getAddClientButton() {
        return addClientButton;
    }

    public void setAddClientButton(JButton addClientButton) {
        this.addClientButton = addClientButton;
    }

    public JTable getTableClients() {
        return tableClients;
    }

    public void setTableClients(JTable tableClients) {
        this.tableClients = tableClients;
    }
}
