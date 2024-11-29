package fragrancestore.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.fragrancestore.connection.DbCon;
import com.fragrancestore.model.Cart;
import com.fragrancestore.model.Order;
import com.fragrancestore.model.Product;
import com.fragrancestore.model.User;

import fragrancestore.dao.OrderDao;
import fragrancestore.dao.ProductDao;

@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            HttpSession session = request.getSession();
            User auth = (User) session.getAttribute("auth");

            if (auth == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart-list");
            if (cartList == null || cartList.isEmpty()) {
                session.setAttribute("popupMessage", "Your cart is empty.");
                response.sendRedirect("cart.jsp");
                return;
            }

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            OrderDao orderDao = new OrderDao(DbCon.getConnection());
            ProductDao productDao = new ProductDao(DbCon.getConnection());

            for (Cart cartItem : cartList) {
                Order order = new Order();
                order.setUserId(auth.getId());
                order.setOrderQuantity(cartItem.getQuantity());
                order.setOrderDate(formatter.format(date));
                order.setTotalPrice(cartItem.getPrice() * cartItem.getQuantity());


                Product product = productDao.getProductById(cartItem.getProductId());
                ArrayList<Product> products = new ArrayList<>();
                products.add(product);
                order.setProducts(products);

                if (!orderDao.insertOrder(order)) {
                    session.setAttribute("popupMessage", "Order failed. Please try again.");
                    response.sendRedirect("cart.jsp");
                    return;
                }
            }


            session.removeAttribute("cart-list");
            response.sendRedirect("orders.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
