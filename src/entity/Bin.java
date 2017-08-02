package entity;

import java.util.ArrayList;
import java.util.List;

public class Bin {
    private static List<Item> items = null;
    public static int COUNTER = 0;
    public static Double PRICE = 0D;


    private Bin() {
    }

    public static List<Item> getItems() {

        if (items == null) {
            items = new ArrayList<>();
        }

        return items;
    }

}
