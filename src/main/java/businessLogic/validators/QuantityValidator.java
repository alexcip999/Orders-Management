package businessLogic.validators;

import model.Order;

import javax.swing.*;

public class QuantityValidator implements Validator<Order>{
    public int requestQuantity;
    public boolean valid;
    @Override
    public void validate(Order order) {
        valid = true;
        if(order.getQuantity() - requestQuantity < 0) {
            JOptionPane.showMessageDialog(null, "there are not enough products in stock");
            valid = false;
        }
    }

    public QuantityValidator(int requestQuantity, boolean valid) {
        this.requestQuantity = requestQuantity;
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
