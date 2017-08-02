package servlet;

import entity.Bin;
import org.apache.log4j.Logger;
import service.ItemService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adminPage")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Logger logger = Logger.getLogger(AdminServlet.class);
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");


        String redirect = null;
        if (request.getParameter("user") != null) {
            logger.info("Администратор нажал на кнопку \"Список Пользователей\"");
            UserService userService = new UserService();
            request.getSession().setAttribute("collectionUser", userService.getAll());
            redirect = "/WEB-INF/views/userList.jsp";
        } else if (request.getParameter("item") != null) {
            logger.info("Администратор нажал на кнопку \"Список Товара\"");
            ItemService itemService = new ItemService();
            request.getSession().setAttribute("collectionItem", itemService.getAll());
            redirect = "/WEB-INF/views/itemList.jsp";
        }
        if (request.getParameter("exit") != null) {
            logger.info("Администратор нажал на кнопку \"Выход\"");

            Bin.getItems().clear();
            request.getSession().invalidate();
            redirect = "/main.jsp";

        }

        request.getRequestDispatcher(redirect).forward(request, response);


    }
}
