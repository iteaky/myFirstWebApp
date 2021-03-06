package servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BlockServlet")
public class BlockServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(BlockServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String userName = (String) request.getSession().getAttribute("name");
        logger.info("Заблокированный пользователь " + userName + "  нажал на кнопку \"Выход\"");
        request.getSession().invalidate();
        request.getRequestDispatcher("/main.jsp").forward(request, response);


    }
}
