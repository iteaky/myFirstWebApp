package service;

import dao.UserDAO;
import entity.User;
import org.apache.log4j.Logger;
import util.ConnectionUtil;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserService extends ConnectionUtil implements UserDAO {


    private static final Logger logger = Logger.getLogger(UserService.class);

    @Override
    public void add(User user) {

        Connection connection = getConnection();
        String sql = "INSERT INTO user (name, isBlocked, paymentStatus, paymentDate) VALUES (?,FALSE,DEFAULT ,DEFAULT );";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.execute();

        } catch (SQLException e) {
            logger.error("Ошибка при добалении нового пользователя");
        } finally {

            if (connection != null) {
                closeConnection(connection);
            }

        }
    }


    @Override
    public List<User> getAll() {
        Connection connection = getConnection();

        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM user";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setIsBlocked(resultSet.getBoolean("isBlocked"));
                user.setId(resultSet.getInt("id"));
                user.setPaymentDate(resultSet.getString("paymentDate"));
                user.setPaymentStatus(resultSet.getBoolean("paymentStatus"));
                userList.add(user);
            }
        } catch (SQLException e) {

            logger.error("Ошибка при получении списка всех пользователей");

        } finally {
            if (connection != null) {
                closeConnection(connection);
            }

        }


        return userList;
    }

    @Override
    public User newOrNot(String name) {
        Connection connection = getConnection();

        User user = new User();
        user.setName("new");
        String sql = "SELECT * FROM user WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setName("exist");
                user.setIsBlocked(resultSet.getBoolean("isBlocked"));
                user.setId(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            logger.error("Ошибка при получении экземпляра пользователя по имени.");
        } finally {
            if (connection != null) {
                closeConnection(connection);
            }
        }


        return user;
    }

    @Override
    public User getById(long id) {
        Connection connection = getConnection();
        User user = new User();//поменять
        String sql = "SELECT * FROM user WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setName(resultSet.getString("name"));
                user.setIsBlocked(resultSet.getBoolean("isBlocked"));
                user.setId(resultSet.getInt("id"));
                user.setPaymentDate(resultSet.getString("paymentDate"));
                user.setPaymentStatus(resultSet.getBoolean("paymentStatus"));
            }
        } catch (SQLException e) {
            logger.error("Ошибка при получении экземпляра пользователя по индификатору");
        }
        closeConnection(connection);


        return user;
    }

    @Override
    public List<User> getAllSortASC() {
        Connection connection = getConnection();

        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM user ORDER BY name";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setIsBlocked(resultSet.getBoolean("isBlocked"));
                user.setId(resultSet.getInt("id"));
                user.setPaymentDate(resultSet.getString("paymentDate"));
                user.setPaymentStatus(resultSet.getBoolean("paymentStatus"));
                userList.add(user);
            }
        } catch (SQLException e) {

            logger.error("Ошибка при получении списка всех пользователей с прямой сортировкой");

        } finally {
            if (connection != null) {
                closeConnection(connection);
            }

        }


        return userList;
    }

    @Override
    public List<User> getAllSortDESC() {
        Connection connection = getConnection();

        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM user ORDER BY name DESC ";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setIsBlocked(resultSet.getBoolean("isBlocked"));
                user.setId(resultSet.getInt("id"));
                user.setPaymentDate(resultSet.getString("paymentDate"));
                user.setPaymentStatus(resultSet.getBoolean("paymentStatus"));
                userList.add(user);
            }
        } catch (SQLException e) {

            logger.error("Ошибка при получении списка всех пользователей с обратной сортировкой");

        } finally {
            if (connection != null) {
                closeConnection(connection);
            }

        }


        return userList;
    }


    @Override
    public void block(int userId, boolean isBlocked) {
        Connection connection = getConnection();

        String sql = "UPDATE user SET isBlocked = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setBoolean(1, !isBlocked);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Ошибка при обновлении статуса блокировки пользователя. ");
        } finally {

            if (connection != null) {
                closeConnection(connection);
            }


        }
    }

    @Override
    public void updateShopDate(String userName) {
        Connection connection = getConnection();


        String sql = "UPDATE user SET paymentDate = ? WHERE name = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDate.now()));
            preparedStatement.setString(2, userName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Ошибка при обновлении даты последнего занесения товара в корзину");
        } finally {

            if (connection != null) {
                closeConnection(connection);
            }


        }
    }


    @Override
    public void updatePaymentStatus(String userName, boolean isPayed) {
        Connection connection = getConnection();

        String sql = "UPDATE user SET paymentStatus = ? WHERE name = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setBoolean(1, isPayed);
            preparedStatement.setString(2, userName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Ошибка при обновлении статуса оплаты покупки.");
        } finally {

            if (connection != null) {
                closeConnection(connection);
            }
        }

    }

    @Override
    public void remove(User user) {
        Connection connection = getConnection();


        String sql = "DELETE FROM user WHERE name = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Ошибка при удалении пользователя по имени");
        } finally {
            if (connection != null) {
                closeConnection(connection);
            }
        }
    }
}