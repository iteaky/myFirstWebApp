package dao;

import entity.Item;
import entity.User;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public  interface ItemDAO {


    //create
    void add(Item item);

    //read
     List<Item> getAll();

    List<Item> getAllSortNameASC();

    List<Item> getAllSortNameDESC();

    List<Item> getAllSortPriceASC();

    List<Item> getAllSortPriceDESC();

    Item getById(int id);


    //update
    void update(int id, String name, Double price);

    //delete
    void remove(int id);


}
