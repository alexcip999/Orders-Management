package model;

import java.util.Date;

public class Order {
    private int id;
    private String nameClient;
    private String nameProduct;
    private int quantity;
    private Double priceProduct;
    private Date date;

    public Order(int id, String nameClient, String nameProduct, int quantity, Double priceProduct, Date date) {
        super();
        this.id = id;
        this.nameClient = nameClient;
        this.nameProduct = nameProduct;
        this.quantity = quantity;
        this.priceProduct = priceProduct;
        this.date = date;
    }

    public Order(String nameClient, String nameProduct, int quantity, Double priceProduct, Date date) {
        super();
        this.nameClient = nameClient;
        this.nameProduct = nameProduct;
        this.quantity = quantity;
        this.priceProduct = priceProduct;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(Double priceProduct) {
        this.priceProduct = priceProduct;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", nameClient='" + nameClient + '\'' +
                ", nameProduct='" + nameProduct + '\'' +
                ", quantity=" + quantity +
                ", priceProduct=" + priceProduct +
                ", date=" + date +
                '}';
    }
}
