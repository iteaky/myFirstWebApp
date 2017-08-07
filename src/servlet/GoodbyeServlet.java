package servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GoodbyeServlet")
public class GoodbyeServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(GoodbyeServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String userName = (String) request.getSession().getAttribute("name");

        if (request.getParameter("items") != null) {

            logger.info("Пользователь " + userName + "  нажал на кнопку \"Купить еще \"");
            request.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(request, response);

        }

        if (request.getParameter("exit") != null) {

            logger.info("Пользователь " + userName + "  нажал на кнопку \"Выход\"");

            request.getSession().invalidate();
            request.getRequestDispatcher("/main.jsp").forward(request, response);

        }
    }
}
