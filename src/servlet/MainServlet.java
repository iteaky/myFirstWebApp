package servlet;

import entity.UserCart;
import entity.User;
import org.apache.log4j.Logger;
import service.ItemService;
import service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/welcomePage")
public class MainServlet extends javax.servlet.http.HttpServlet {

    private final static Logger logger = Logger.getLogger(MainServlet.class);


    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        HttpSession session = request.getSession();
        UserService userService = new UserService();
        User user = userService.newOrNot(request.getParameter("user"));
        String userName = user.getName();
        boolean admin = (request.getParameter("Администратор") != null);

        if (admin) {

            logger.info("произведен вход за Администратора.");
            request.getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(request, response);

        } else {

            if (user.getIsBlocked()) {

                logger.info("введено имя " + request.getParameter("user") + "  -заблокированный позьзователь");
                request.getRequestDispatcher("/WEB-INF/views/block.jsp").forward(request, response);
            } else if (userName.equals("new")) {

                logger.info("Введено имя пользователя  " + request.getParameter("user") + " , который еще не заходил в магазин");
                user = new User(request.getParameter("user"));
                userService.add(user);
            }

            logger.info("произведен вход за покупателя " + request.getParameter("user") + "  уже посещавшего магазин.");
            ItemService itemService = new ItemService();
            session.setAttribute("collectionItem", itemService.getAll());
            session.setAttribute("name", request.getParameter("user"));
            session.setAttribute("user", userService.getById(user.getId()));
            UserCart cart = new UserCart();
            cart.setPrice(0D);
            cart.setCounter(0);
            session.setAttribute("cart",cart);
            request.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(request, response);
        }
    }
}

