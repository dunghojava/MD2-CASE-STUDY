package model;

import java.io.Serializable;

public class Revenue implements Serializable {
    private int id;
    private double price;
    private String name;

    public Revenue() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Revenue(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Revenue{" +
                "price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
