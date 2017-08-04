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

   private final static  Logger logger = Logger.getLogger(BinServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String userName = (String) request.getSession().getAttribute("name");
        Bin bin = (Bin) request.getSession().getAttribute("bin");


        if (request.getParameter("exit") != null) {

            logger.info("Пользователь " + userName + " нажал на кнопку \"Выход\"");
            request.getSession().invalidate();
            bin.getItems().clear();
            bin.setCounter(0);
            bin.setPrice(0D);
            request.getSession().setAttribute("counter",0);
            request.getSession().setAttribute("binPrice", 0);
            request.getSession().setAttribute("bin", bin);
            request.getRequestDispatcher("/main.jsp").forward(request, response);

        }

        if (request.getParameter("delete") != null) {

            logger.info("Пользователь " + userName + " удаляет товар из корзины");

            for (Item item : bin.getItems()) {

                if (item.getName().equals(request.getParameter("delete"))) {

                    bin.getItems().remove(item);
                    bin.setPrice(bin.getPrice()- item.getPrice());
                    bin.setCounter(bin.getCounter()-1);

                    break;
                }
            }
            request.getSession().setAttribute("binPrice", bin.getPrice());
            request.getSession().setAttribute("counter", bin.getCounter());
            request.getSession().setAttribute("bin", bin);

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
            bin.getItems().clear();
            bin.setCounter(0);
            bin.setPrice(0D);
            request.getSession().setAttribute("counter",0);
            request.getSession().setAttribute("binPrice", 0);
            request.getSession().setAttribute("bin", bin);
            request.getRequestDispatcher("/WEB-INF/views/goodbye.jsp").forward(request, response);
        }
    }

}
