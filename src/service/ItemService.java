package service;

import businessLogic.Util;
import dao.ItemDAO;
import entity.Item;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemService extends Util implements ItemDAO {

    private Logger logger = Logger.getLogger(ItemService.class);


    @Override
    public void add(Item item) {
        Connection connection = getConnection();
        String sql = "INSERT INTO eshop.item (name,price) VALUES (?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.execute();

        } catch (SQLException e) {
            logger.error("Ошибка при добавлении нового товара адмиистратором.");

        } finally {
            if (connection != null) {
                closeConnection(connection);
            }
        }
    }

    @Override
    public List<Item> getAll() {
        Connection connection = getConnection();

        List<Item> itemList = new ArrayList<>();

        String sql = "SELECT * FROM item";


        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Item item = new Item();
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getDouble("price"));
                item.setId(resultSet.getLong("id"));
                itemList.add(item);

            }
        } catch (SQLException e) {
            logger.error("Ошибка при получении списка всех товаров");
        } finally {

            if (connection != null) {
                closeConnection(connection);
            }


        }


        return itemList;
    }

    @Override
    public List<Item> getAllSortNameASC() {

        Connection connection = getConnection();

        List<Item> itemList = new ArrayList<>();

        String sql = "SELECT * FROM item ORDER BY name";


        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Item item = new Item();
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getDouble("price"));
                item.setId(resultSet.getLong("id"));
                itemList.add(item);

            }
        } catch (SQLException e) {
            logger.error("Ошибка при получении списка всех товаров");
        } finally {

            if (connection != null) {
                closeConnection(connection);
            }


        }


        return itemList;
    }

    @Override
    public List<Item> getAllSortNameDESC() {

        Connection connection = getConnection();

        List<Item> itemList = new ArrayList<>();

        String sql = "SELECT * FROM item ORDER BY name DESC ";


        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Item item = new Item();
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getDouble("price"));
                item.setId(resultSet.getLong("id"));
                itemList.add(item);

            }
        } catch (SQLException e) {
            logger.error("Ошибка при получении списка всех товаров");
        } finally {

            if (connection != null) {
                closeConnection(connection);
            }


        }


        return itemList;
    }

    @Override
    public List<Item> getAllSortPriceASC() {

        Connection connection = getConnection();

        List<Item> itemList = new ArrayList<>();

        String sql = "SELECT * FROM item ORDER BY price";


        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Item item = new Item();
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getDouble("price"));
                item.setId(resultSet.getLong("id"));
                itemList.add(item);

            }
        } catch (SQLException e) {
            logger.error("Ошибка при получении списка всех товаров");
        } finally {

            if (connection != null) {
                closeConnection(connection);
            }


        }


        return itemList;
    }

    @Override
    public List<Item> getAllSortPriceDESC() {

        Connection connection = getConnection();

        List<Item> itemList = new ArrayList<>();

        String sql = "SELECT * FROM item ORDER BY price DESC ";


        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Item item = new Item();
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getDouble("price"));
                item.setId(resultSet.getLong("id"));
                itemList.add(item);

            }
        } catch (SQLException e) {
            logger.error("Ошибка при получении списка всех товаров");
        } finally {

            if (connection != null) {
                closeConnection(connection);
            }


        }


        return itemList;
    }


    @Override
    public Item getById(int id) {
        Connection connection = getConnection();

        Item item = new Item();
        String sql = "SELECT * FROM item WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                item.setId(resultSet.getLong("id"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getDouble("price"));
            }
        } catch (SQLException e) {
            logger.error("Ошибка при получении экземпляра товара по индификатору.");
        } finally {
            if (connection != null) {
                closeConnection(connection);
            }
        }
        return item;
    }

    @Override
    public void update(int id, String name, Double price) {
        Connection connection = getConnection();

        if (price != null & !name.equals("") || price != null & !name.equals("")) {

            String sql = "UPDATE item SET name = ? WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, name);
                preparedStatement.setLong(2, id);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {

                logger.error("Ошибка при обновлении имени товара администратором");

            } finally {

                if (connection != null) {

                    closeConnection(connection);

                }
            }
        } else if (name.equals("") & price != null || price != null & !name.equals("")) {

            String sql = "UPDATE item SET price = ? WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setDouble(1, price);
                preparedStatement.setLong(2, id);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {

                logger.error("Ошибка при обновлении имени товара администратором");

            } finally {

                if (connection != null) {

                    closeConnection(connection);

                }
            }
        }
    }

    @Override
    public void remove(int id) {
        Connection connection = getConnection();
        String sql = "DELETE FROM item WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            logger.error("Ошибка при удалении товара из общего списка администратором.");

        } finally {

            if (connection != null) {
                closeConnection(connection);
            }
        }
    }
}
