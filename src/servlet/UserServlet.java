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

    private final static Logger logger = Logger.getLogger(UserServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userName = (String) request.getSession().getAttribute("name");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        ItemService itemService = new ItemService();
        Bin bin = (Bin) request.getSession().getAttribute("bin");

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
            bin.setCounter(0);
            bin.setPrice(0D);
            request.getSession().setAttribute("counter",0);
            request.getSession().setAttribute("binPrice", 0);
            bin.getItems().clear();
            request.getSession().setAttribute("bin",bin);
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

            bin.getItems().add(item);
            bin.setPrice(0D);
            for (Item i: bin.getItems()){
                bin.setPrice(bin.getPrice()+i.getPrice());
            }
            bin.setCounter(bin.getCounter()+1);
            session.setAttribute("binPrice", bin.getPrice());
            session.setAttribute("counter", bin.getCounter());
            session.setAttribute("userBin", bin.getItems());
            request.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(request, response);
            logger.info("Пользователь " + userName + "  добавил товар " + item.getName() + " в корзину");
        }
    }
}