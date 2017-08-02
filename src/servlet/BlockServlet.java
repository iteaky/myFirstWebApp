package servlet;

import entity.Bin;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BlockServlet")
public class BlockServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String userName = (String) request.getSession().getAttribute("name");
        Logger logger = Logger.getLogger(BlockServlet.class);
        logger.info("Заблокированный пользователь " + userName + "  нажал на кнопку \"Выход\"");
        Bin.getItems().clear();
        request.getSession().invalidate();
        request.getRequestDispatcher("/main.jsp").forward(request, response);


    }
}
