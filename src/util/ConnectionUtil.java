package util;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;


public class ConnectionUtil {
 private final static   Logger logger = Logger.getLogger(ConnectionUtil.class);



    //драйвер



    private final static String URL = "jdbc:mysql://localhost:3306/eshop?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF8";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";


    private static final ArrayList<Connection> pool = new ArrayList<>();

        static {


            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                logger.error("Драйвер не найден");
            }



            for (int i = 0; i < 5; i++) {
                try {
                    pool.add(DriverManager.getConnection(URL,USERNAME,PASSWORD));
                } catch (SQLException e) {
                    logger.error("Отсутствует поделючение к базе данных");
                }

            }
        }

    protected Connection getConnection() {
            if(pool.isEmpty()){
                return null;
            }

        return pool.remove(pool.size()-1);
    }


    protected static void  closeConnection(Connection connection) {
            pool.add(connection);
            connection=null;
    }



}
