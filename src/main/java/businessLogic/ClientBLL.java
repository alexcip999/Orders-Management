package businessLogic;

import businessLogic.validators.ClientAgeValidator;
import businessLogic.validators.EmailValidator;
import businessLogic.validators.Validator;
import dataAccess.ClientDAO;
import model.Client;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL {
    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;
    public ClientBLL(){
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator(true));
        validators.add(new ClientAgeValidator());
        clientDAO = new ClientDAO();
    }

    public ClientDAO getClientDAO() {
        return clientDAO;
    }
    public Client insert(Client client){
        return clientDAO.insert(client);
    }
    public Client edit(Client client){
        return clientDAO.edit(client);
    }
    public Client delete(int id, String field){
        return clientDAO.delete(id, field);
    }
    public DefaultTableModel display(){
        return clientDAO.display();
    }
}
