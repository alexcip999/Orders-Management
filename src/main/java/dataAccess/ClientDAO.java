package dataAccess;

import conection.ConnectionFactory;
import model.Client;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientDAO extends AbstractDAO<Client>{
    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    public ClientDAO() {
        super(Client.class);
    }

    public Client findById(int clientId){
        return super.findById(clientId);
    }

    public Client insert(Client client){
        return super.insert(client, "clients");
    }

    public DefaultTableModel display(){
        DefaultTableModel tableModel = super.display("clients");
        return tableModel;
    }

    public Client delete(int id, String field){
        return super.delete(id, field);
    }

    public Client edit(Client client){
        return super.edit(client, "clients");
    }
}
