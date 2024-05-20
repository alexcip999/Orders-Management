package businessLogic.validators;

import model.Product;

import javax.swing.*;

public class StockValidator implements Validator<Product>{
    private boolean valid = true;
    @Override
    public void validate(Product product) {
        valid = true;
        if(product.getQuantityAvailable() == 0){
            JOptionPane.showMessageDialog(null, product.getNameProduct() + "it is no longer in stock");
            valid = false;
        }
    }

    public StockValidator(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
