package model;

public class Product {
    private int id;
    private String nameProduct;
    private Double price;
    private int quantityAvailable;

    public Product(int idProduct, String nameProduct, Double price, int quantityAvailable) {
        super();
        this.id = idProduct;
        this.nameProduct = nameProduct;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
    }

    public Product(String nameProduct, Double price, int quantityAvailable) {
        super();
        this.nameProduct = nameProduct;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
    }

    public int getIdProduct() {
        return id;
    }

    public void setIdProduct(int idProduct) {
        this.id = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + id +
                ", nameProduct='" + nameProduct + '\'' +
                ", price=" + price +
                ", QuantityAvailable=" + quantityAvailable +
                '}';
    }
}
