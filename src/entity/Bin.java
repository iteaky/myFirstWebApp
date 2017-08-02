package entity;

import java.util.ArrayList;
import java.util.List;

public class Bin {
    private static List<Item> items = null;
    public static int COUNTER = 0;
    public static int PRICE = 0;


    private Bin() {
    }

    public static List<Item> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }

        return items;
    }

}
