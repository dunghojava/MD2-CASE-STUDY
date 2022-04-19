package model;

import java.io.Serializable;

public class Food implements Serializable {
    private int id;
    private String name;
    private int price;

    public Food() {
    }

    public Food(int id, String name, int price) {
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return '\n' + "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
