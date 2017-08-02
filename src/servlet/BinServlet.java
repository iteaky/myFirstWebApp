package servlet;

import entity.Bin;
import entity.Item;
import org.apache.log4j.Logger;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BinServlet")
public class BinServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String userName = (String) request.getSession().getAttribute("name");
        Logger logger = Logger.getLogger(BinServlet.class);

        if (request.getParameter("exit") != null) {

            logger.info("Пользователь " + userName + " нажал на кнопку \"Выход\"");
            Bin.getItems().clear();
            request.getSession().invalidate();
            Bin.COUNTER = 0;
            Bin.PRICE = 0D;
            request.getRequestDispatcher("/main.jsp").forward(request, response);

        }

        if (request.getParameter("delete") != null) {

            logger.info("Пользователь " + userName + " удаляет товар из корзины");

            for (Item item : Bin.getItems()) {

                if (item.getName().equals(request.getParameter("delete"))) {

                    Bin.getItems().remove(item);
                    Bin.PRICE -= item.getPrice();
                    break;
                }
            }
            request.getSession().setAttribute("binPrice", Bin.PRICE);
            request.getSession().setAttribute("counter", --Bin.COUNTER);
            request.getRequestDispatcher("/WEB-INF/views/bin.jsp").forward(request, response);


        }

        if (request.getParameter("back") != null) {

            logger.info("Пользователь " + userName + "  нажал на кнопку \"Назад\" (из корзины к списку товаров)");
            request.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(request, response);
        }
        if (request.getParameter("bin") != null) {

            logger.info("Пользователь " + userName + "  нажал на кнопку \"Оплатить товар\"");
            UserService userService = new UserService();
            userService.updatePaymentStatus((String) request.getSession().getAttribute("name"), true);
            Bin.getItems().clear();
            Bin.COUNTER = 0;
            Bin.PRICE = 0D;
            request.getRequestDispatcher("/WEB-INF/views/goodbye.jsp").forward(request, response);
        }
    }

}
