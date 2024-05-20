package businessLogic;

import businessLogic.validators.Validator;
import dataAccess.OrdersDAO;
import dataAccess.ProductDAO;
import model.Order;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductBLL {
    private List<Validator<Product>> validators;
    private ProductDAO productDAO;
    public void ProductBLL(int quantity){
        validators = new ArrayList<Validator<Product>>();
        productDAO = new ProductDAO();
    }

    public ProductDAO getProductDAO() {
        return productDAO;
    }
}
