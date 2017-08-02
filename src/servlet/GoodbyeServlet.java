package servlet;

import entity.Bin;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GoodbyeServlet")
public class GoodbyeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Logger logger = Logger.getLogger(GoodbyeServlet.class);
        if (request.getParameter("items") != null) {
            logger.info("Пользователь нажал на кнопку \"Купить еще \"");

            request.getSession().setAttribute("counter", Bin.COUNTER);
            request.getSession().setAttribute("binPrice", Bin.COUNTER);
            request.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(request, response);

        }

        if (request.getParameter("exit") != null) {
            logger.info("Пользователь нажал на кнопку \"Выход\"");
            Bin.getItems().clear();
            request.getSession().invalidate();
            request.getRequestDispatcher("/main.jsp").forward(request, response);

        }
    }
}
