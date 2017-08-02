package dao;

import entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    //create

    void add(User user);

    //read

    List<User> getAll();

    User newOrNot(String name);

    User getById(long id);

    List<User> getAllSortASC();

    List<User> getAllSortDESC();


    //update

    void block(int userId, boolean isBlocked);

    void updateShopDate(String userName);

    void updatePaymentStatus(String userName, boolean isPayed);

    //delete

    void remove(User user) throws SQLException;


}
