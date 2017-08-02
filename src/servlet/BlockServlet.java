package servlet;

import entity.Bin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.log4j.Logger;

@WebServlet("/BlockServlet")
public class BlockServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Logger logger = Logger.getLogger(BlockServlet.class);
        logger.info("Заблокированный пользователь нажал на кнопку \"Выход\"");

        Bin.getItems().clear();
        request.getSession().invalidate();
        request.getRequestDispatcher("/main.jsp").forward(request, response);


    }
}
