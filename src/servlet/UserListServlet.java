package servlet;

import entity.User;
import org.apache.log4j.Logger;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Logger logger = Logger.getLogger(UserListServlet.class);

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        UserService userService = new UserService();


        if (request.getParameter("sortUp") != null) {
            logger.info("Администратор нажал на кнопку \"Сортировка пользвателей по имени по убыванию\" на странице списка пользователей");

            request.getSession().setAttribute("collectionUser", userService.getAllSortASC());
            request.getRequestDispatcher("/WEB-INF/views/userList.jsp").forward(request, response);


        } else if (request.getParameter("sortDown") != null) {
            logger.info("Администратор нажал на кнопку \"Сортировка пользвателей по имени по возрастанию\" на странице списка пользователей");

            request.getSession().setAttribute("collectionUser", userService.getAllSortDESC());
            request.getRequestDispatcher("/WEB-INF/views/userList.jsp").forward(request, response);

        }
        if (request.getParameter("back") != null) {
            logger.info("Администратор нажал на кнопку \"Назад\" на странице списка пользователей");

            request.getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(request, response);

        } else {
            int userId = Integer.parseInt(request.getParameter("block"));
            logger.info("Администратор нажал на кнопку \"Блокировка пользователя\" на странице списка пользователей");
            userService.block(userId, userService.getById(userId).getIsBlocked());
            request.setAttribute("collectionUser", userService.getAll());
            request.getRequestDispatcher("/WEB-INF/views/userList.jsp").forward(request, response);


        }
    }
}
