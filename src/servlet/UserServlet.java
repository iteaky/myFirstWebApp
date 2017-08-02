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
import java.util.ArrayList;
import java.util.List;


@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
        String userName = (String) request.getSession().getAttribute("name");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        Logger logger = Logger.getLogger(UserServlet.class);
        ItemService itemService = new ItemService();

        if (request.getParameter("sortUpName") != null) {


            logger.info("Пользователь " + userName + "  нажал на кнопку \"Сортировка товара по имени по убыванию\" на странице списка товара");
            request.getSession().setAttribute("collectionItem", itemService.getAllSortNameASC());
            request.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(request, response);

        }
        else if (request.getParameter("sortDownName") != null) {

            logger.info("Пользователь " + userName + "  нажал на кнопку \"Сортировка товара по имени по возрастанию\" на странице списка товара");
            request.getSession().setAttribute("collectionItem", itemService.getAllSortNameDESC());
            request.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(request, response);


        } else if (request.getParameter("sortUpPrice") != null) {

            logger.info("Пользователь " + userName + "  нажал на кнопку \"Сортировка товара по цене по убыванию\" на странице списка товара");
            request.getSession().setAttribute("collectionItem", itemService.getAllSortPriceASC());
            request.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(request, response);


        } else if (request.getParameter("sortDownPrice") != null) {

            logger.info("Пользователь " + userName + "  нажал на кнопку \"Сортировка товара по цене по возрастанию\" на странице списка товара");
            request.getSession().setAttribute("collectionItem", itemService.getAllSortPriceDESC());
            request.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(request, response);
        }

        if (request.getParameter("exit") != null) {

            logger.info("Пользователь " + userName + "  нажал на кнопку \"Выход\" на странице списка товара");
            request.getSession().invalidate();
            Bin.setCOUNTER(0);
            Bin.setPRICE(0D);
            Bin.getItems().clear();
            request.getRequestDispatcher("/main.jsp").forward(request, response);

        } else if (request.getParameter("bin") != null) {

            logger.info("Пользователь " + userName + "  нажал на кнопку  \"перейти в корзину\" на странице списка товара");
            request.getRequestDispatcher("/WEB-INF/views/bin.jsp").forward(request, response);

        } else if (request.getParameter("add") != null) {

            UserService userService = new UserService();
            userService.updatePaymentStatus((String) request.getSession().getAttribute("name"), false);
            HttpSession session = request.getSession();
            String userId = request.getParameter("add");
            Item item = itemService.getById(Integer.parseInt(userId));
            userService.updateShopDate((String) request.getSession().getAttribute("name"));

            Bin.getItems().add(item);
            Bin.setPRICE(0D);
            for (Item i: Bin.getItems()){
                Bin.setPRICE(Bin.getPRICE()+i.getPrice());
            }
            Bin.setCOUNTER(Bin.getCOUNTER()+1);
            session.setAttribute("binPrice", Bin.getPRICE());
            session.setAttribute("counter", Bin.getCOUNTER());
            session.setAttribute("userBin", Bin.getItems());
            request.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(request, response);
            logger.info("Пользователь " + userName + "  добавил товар " + item.getName() + " в корзину");
        }
    }
}
