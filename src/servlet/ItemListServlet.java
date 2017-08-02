package servlet;

import entity.Item;
import org.apache.log4j.Logger;
import service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ItemListServlet")
public class ItemListServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Logger logger = Logger.getLogger(ItemListServlet.class);

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        ItemService itemService = new ItemService();

        if (request.getParameter("sortUpName") != null) {
            logger.info("Администратор нажал на кнопку \"Сортировка товара по имени по убыванию\" на странице списка товара");

            request.getSession().setAttribute("collectionItem", itemService.getAllSortNameASC());
            request.getRequestDispatcher("/WEB-INF/views/itemList.jsp").forward(request, response);

        } else if (request.getParameter("sortDownName") != null) {
            logger.info("Администратор нажал на кнопку \"Сортировка товара по имени по возрастанию\" на странице списка товара");

            request.getSession().setAttribute("collectionItem", itemService.getAllSortNameDESC());
            request.getRequestDispatcher("/WEB-INF/views/itemList.jsp").forward(request, response);


        } else if (request.getParameter("sortUpPrice") != null) {
            logger.info("Администратор нажал на кнопку \"Сортировка товара по цене по убыванию\" на странице списка товара");

            request.getSession().setAttribute("collectionItem", itemService.getAllSortPriceASC());
            request.getRequestDispatcher("/WEB-INF/views/itemList.jsp").forward(request, response);


        } else if (request.getParameter("sortDownPrice") != null) {
            logger.info("Администратор нажал на кнопку \"Сортировка товара по цене по возрастанию\" на странице списка товара");

            request.getSession().setAttribute("collectionItem", itemService.getAllSortPriceDESC());
            request.getRequestDispatcher("/WEB-INF/views/itemList.jsp").forward(request, response);


        } else if (request.getParameter("back") != null) {
            logger.info("Администратор нажал на кнопку \"назад\" на странице списка товара");

            request.getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(request, response);

        } else {

            if (request.getParameter("add") != null & !request.getParameter("newPrice").equals("") & !request.getParameter("newName").equals("")) {
                logger.info("Администратор нажал на кнопку \"добавить новый товар\", предварительно заполнив поле название и цена на странице списка товара");

                Item item = new Item();

                item.setPrice(Integer.parseInt(request.getParameter("newPrice")));
                item.setName(request.getParameter("newName"));
                itemService.add(item);


            }

            String idUserToDelete = request.getParameter("delete");
            if (idUserToDelete != null) {
                logger.info("Администратор нажал на кнопку \"удалить товар\" на странице списка товара");

                itemService.remove(Integer.parseInt(idUserToDelete));


            }

            if (request.getParameter("change") != null) {
                logger.info("Администратор нажал на кнопку \"Изменить имя или цену товара\" на странице списка товара");

                int id = Integer.parseInt(request.getParameter("change"));
                String itemPriceString = request.getParameter("price" + id);
                String itemPriseStringForCast = (itemPriceString.equals("")) ? "0" : itemPriceString;
                int itemPrice = Integer.parseInt(itemPriseStringForCast);
                String itemName = request.getParameter("name" + id);
                itemService.update(id, itemName, itemPrice);
            }

            request.setAttribute("collectionItem", itemService.getAll());
            request.getRequestDispatcher("/WEB-INF/views/itemList.jsp").forward(request, response);

        }
    }
}
