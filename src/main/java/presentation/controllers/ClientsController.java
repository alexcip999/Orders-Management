package presentation.controllers;

import businessLogic.ClientBLL;
import businessLogic.validators.EmailValidator;
import dataAccess.ClientDAO;
import model.Client;
import presentation.view.ViewClients;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientsController implements ActionListener {
    private final ViewClients viewClients;
    public DefaultTableModel tableModel;
    public int selectedRowIndex = 0;
    public ClientBLL clientBLL = new ClientBLL();

    public ClientsController(ViewClients viewClients) {
        this.viewClients = viewClients;
        this.viewClients.addClientButtonListener(this);
        this.viewClients.editClientButtonListener(this);
        this.viewClients.deleteClientButtonListener(this);
        this.viewClients.viewClientsButtonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "add client":
                JTextField nameField = new JTextField();
                JTextField addressField = new JTextField();
                JTextField emailField = new JTextField();
                JTextField ageField = new JTextField();
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.add(new JLabel("Name "));
                panel.add(nameField);
                panel.add(Box.createVerticalStrut(10));
                panel.add(new JLabel("Address "));
                panel.add(addressField);
                panel.add(Box.createVerticalStrut(10));
                panel.add(new JLabel("Email "));
                panel.add(emailField);
                panel.add(Box.createVerticalStrut(10));
                panel.add(new JLabel("Age "));
                panel.add(ageField);
                int result = JOptionPane.showConfirmDialog(null, panel, "Enter Values",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    String name = nameField.getText();
                    String address = addressField.getText();
                    String email = emailField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    Client client = new Client(name, address, email, age);
                    EmailValidator emailValidator = new EmailValidator(true);
                    emailValidator.validate(client);
                    if(emailValidator.isValid()){
                        clientBLL.insert(client);
                        tableModel = clientBLL.display();
                        viewClients.getTableClients().setModel(tableModel);
                    }

                }
                break;
            case "view clients":
                tableModel = clientBLL.display();
                viewClients.getTableClients().setModel(tableModel);
                break;
            case "delete client":
                selectedRowIndex = viewClients.getTableClients().getSelectedRow();
                int id = 0;
                if (selectedRowIndex != -1) {
                    Object[] rowData = new Object[viewClients.getTableClients().getColumnCount()];
                    for (int i = 0; i < viewClients.getTableClients().getColumnCount(); i++) {
                        rowData[i] = viewClients.getTableClients().getValueAt(selectedRowIndex, i);
                    }
                    id = (int) rowData[0];
                    clientBLL.delete(id, "clients");
                } else {
                    JOptionPane.showMessageDialog(null, "No client selected.");
                }
                tableModel = clientBLL.display();
                viewClients.getTableClients().setModel(tableModel);
                break;
            case "edit client":
                JTextField nameEditField = new JTextField();
                JTextField addressEditField = new JTextField();
                JTextField emailEditField = new JTextField();
                JTextField ageEditField = new JTextField();
                JPanel editPanel = new JPanel();
                selectedRowIndex = viewClients.getTableClients().getSelectedRow();
                if (selectedRowIndex != -1) {
                    Object[] rowData = new Object[viewClients.getTableClients().getColumnCount()];
                    for (int i = 0; i < viewClients.getTableClients().getColumnCount(); i++) {
                        rowData[i] = viewClients.getTableClients().getValueAt(selectedRowIndex, i);
                    }
                    int idClient = (int) rowData[0];
                    nameEditField.setText((String) rowData[1]);
                    addressEditField.setText((String) rowData[2]);
                    emailEditField.setText((String) rowData[3]);
                    ageEditField.setText(String.valueOf((int) rowData[4]));
                editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));
                editPanel.add(new JLabel("Name "));
                editPanel.add(nameEditField);
                editPanel.add(Box.createVerticalStrut(10));
                editPanel.add(new JLabel("Address "));
                editPanel.add(addressEditField);
                editPanel.add(Box.createVerticalStrut(10));
                editPanel.add(new JLabel("Email "));
                editPanel.add(emailEditField);
                editPanel.add(Box.createVerticalStrut(10));
                editPanel.add(new JLabel("Age "));
                editPanel.add(ageEditField);
                int editResult = JOptionPane.showConfirmDialog(null, editPanel, "Enter Values",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (editResult == JOptionPane.OK_OPTION) {
                        String name = nameEditField.getText();
                        String address = addressEditField.getText();
                        String email = emailEditField.getText();
                        int age = Integer.parseInt(ageEditField.getText());
                        Client client = new Client(idClient, name, address, email, age);
                        clientBLL.edit(client);
                    }
                    tableModel = clientBLL.display();
                    viewClients.getTableClients().setModel(tableModel);

                }else {
                    JOptionPane.showMessageDialog(null, "No client selected.");
                }
                break;
        }
    }
}
