package entity;

import java.util.ArrayList;
import java.util.List;

public class Bin {
    private static List<Item> items = new ArrayList<>();
    private static int COUNTER = 0;
    private static Double PRICE = 0D;


    private Bin() {
    }

    public static List<Item> getItems() {
        return items;
    }

    public static int getCOUNTER() {
        return COUNTER;
    }

    public static void setCOUNTER(int COUNTER) {
        Bin.COUNTER = COUNTER;
    }

    public static Double getPRICE() {
        return PRICE;
    }

    public static void setPRICE(Double PRICE) {
        Bin.PRICE = PRICE;
    }
}
