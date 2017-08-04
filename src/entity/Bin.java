package entity;

import java.util.ArrayList;
import java.util.List;

public class Bin {
    private List<Item> items = new ArrayList<>();
    private int counter = 0;
    private Double price = 0D;

    public Bin() {
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

