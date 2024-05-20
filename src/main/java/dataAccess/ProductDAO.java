package dataAccess;

import model.Product;

import javax.swing.table.DefaultTableModel;
import java.util.logging.Logger;

public class ProductDAO extends AbstractDAO<Product>{
    protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());

    public ProductDAO() {
        super(Product.class);
    }

    public Product findById(int productId){
        return super.findById(productId);
    }

    public Product insert(Product product){
        return super.insert(product, "products");
    }

    public DefaultTableModel display(){
        DefaultTableModel tableModel = super.display("products");
        return tableModel;
    }
    public Product delete(int id){
        return super.delete(id, "products");
    }
    public Product edit(Product product){
        return super.edit(product, "products");
    }
}
