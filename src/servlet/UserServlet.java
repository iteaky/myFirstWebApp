package servlet;

import entity.Bin;
import entity.Item;
import org.apache.log4j.Logger;
import service.ItemService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Logger logger = Logger.getLogger(UserServlet.class);
        ItemService itemService = new ItemService();

        if (request.getParameter("sortUpName") != null) {
            logger.info("Пользователь нажал на кнопку \"Сортировка товара по имени по убыванию\" на странице списка товара");

            request.getSession().setAttribute("collectionItem", itemService.getAllSortNameASC());
            request.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(request, response);

        } else if (request.getParameter("sortDownName") != null) {
            logger.info("Пользователь нажал на кнопку \"Сортировка товара по имени по возрастанию\" на странице списка товара");

            request.getSession().setAttribute("collectionItem", itemService.getAllSortNameDESC());
            request.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(request, response);


        } else if (request.getParameter("sortUpPrice") != null) {
            logger.info("Пользователь нажал на кнопку \"Сортировка товара по цене по убыванию\" на странице списка товара");

            request.getSession().setAttribute("collectionItem", itemService.getAllSortPriceASC());
            request.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(request, response);


        } else if (request.getParameter("sortDownPrice") != null) {
            logger.info("Пользователь нажал на кнопку \"Сортировка товара по цене по возрастанию\" на странице списка товара");

            request.getSession().setAttribute("collectionItem", itemService.getAllSortPriceDESC());
            request.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(request, response);


        }

        if (request.getParameter("exit") != null) {
            logger.info("Пользователь нажал на кнопку \"Выход\" на странице списка товара");
            request.getSession().invalidate();
            Bin.COUNTER = 0;
            Bin.PRICE = 0;
            Bin.getItems().clear();


            request.getRequestDispatcher("/main.jsp").forward(request, response);

        } else if (request.getParameter("bin") != null) {
            logger.info("Пользователь нажал на кнопку  \"перейти в корзину\" на странице списка товара");
            request.getRequestDispatcher("/WEB-INF/views/bin.jsp").forward(request, response);

        } else {
            UserService userService = new UserService();
            userService.updatePaymentStatus((String) request.getSession().getAttribute("name"), false);
            HttpSession session = request.getSession();
            String userId = request.getParameter("add");
            Item item = itemService.getById(Integer.parseInt(userId));
            userService.updateShopDate((String) request.getSession().getAttribute("name"));
            Bin.getItems().add(item);
            Bin.PRICE += item.getPrice();
            session.setAttribute("binPrice", Bin.PRICE);
            session.setAttribute("counter", ++Bin.COUNTER);
            session.setAttribute("userBin", Bin.getItems());
            request.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(request, response);
            logger.info("Пользователь добавил товар " + item.getName() + " в корзину");


        }


    }
}
