package servlet;

import entity.UserCart;
import entity.Item;
import org.apache.log4j.Logger;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UserCartServlet")
public class UserCartServlet extends HttpServlet {

    private final static  Logger logger = Logger.getLogger(UserCartServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String userName = (String) request.getSession().getAttribute("name");
        UserCart cart = (UserCart) request.getSession().getAttribute("cart");


        if (request.getParameter("exit") != null) {

            logger.info("Пользователь " + userName + " нажал на кнопку \"Выход\"");
            request.getSession().invalidate();
            cart.getItems().clear();
            cart.setCounter(0);
            cart.setPrice(0D);
            request.getSession().setAttribute("counter",0);
            request.getSession().setAttribute("cartPrice", 0);
            request.getSession().setAttribute("cart", cart);
            request.getRequestDispatcher("/main.jsp").forward(request, response);

        }

        if (request.getParameter("delete") != null) {

            logger.info("Пользователь " + userName + " удаляет товар из корзины");

            for (Item item : cart.getItems()) {

                if (item.getName().equals(request.getParameter("delete"))) {

                    cart.getItems().remove(item);
                    cart.setPrice(cart.getPrice()- item.getPrice());
                    cart.setCounter(cart.getCounter()-1);

                    break;
                }
            }
            request.getSession().setAttribute("cartPrice", cart.getPrice());
            request.getSession().setAttribute("counter", cart.getCounter());
            request.getSession().setAttribute("cart", cart);

            request.getRequestDispatcher("/WEB-INF/views/userCart.jsp").forward(request, response);


        }

        if (request.getParameter("back") != null) {

            logger.info("Пользователь " + userName + "  нажал на кнопку \"Назад\" (из корзины к списку товаров)");
            request.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(request, response);
        }
        if (request.getParameter("cart") != null) {

            logger.info("Пользователь " + userName + "  нажал на кнопку \"Оплатить товар\"");
            UserService userService = new UserService();
            userService.updatePaymentStatus((String) request.getSession().getAttribute("name"), true);
            cart.getItems().clear();
            cart.setCounter(0);
            cart.setPrice(0D);
            request.getSession().setAttribute("counter",0);
            request.getSession().setAttribute("cartPrice", 0);
            request.getSession().setAttribute("cart", cart);
            request.getRequestDispatcher("/WEB-INF/views/goodbye.jsp").forward(request, response);
        }
    }

}