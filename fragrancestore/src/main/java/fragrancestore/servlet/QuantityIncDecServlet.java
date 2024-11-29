package fragrancestore.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import com.fragrancestore.model.Cart;

@WebServlet("/quantity-inc-dec") // This must match the URL being accessed
public class QuantityIncDecServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        int productId = Integer.parseInt(request.getParameter("id"));

        HttpSession session = request.getSession();
        ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart-list");

        if (action != null && productId > 0 && cartList != null) {

            if (action.equals("inc")) {
                for (Cart cartItem : cartList) {
                    if (cartItem.getProductId() == productId) {
                        cartItem.setQuantity(cartItem.getQuantity() + 1); // Increment quantity
                        break;
                    }
                }
            }

            else if (action.equals("dec")) {
                for (Cart cartItem : cartList) {
                    if (cartItem.getProductId() == productId && cartItem.getQuantity() > 1) {
                        cartItem.setQuantity(cartItem.getQuantity() - 1); // Decrement quantity
                        break;
                    }
                }
            }
        }


        response.sendRedirect("cart.jsp");
    }
}
