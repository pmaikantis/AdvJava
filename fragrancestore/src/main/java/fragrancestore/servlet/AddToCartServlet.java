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

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String message = ""; 

        try {
           
            int id = Integer.parseInt(request.getParameter("id"));

            Cart cartItem = new Cart();
            cartItem.setProductId(id);
            cartItem.setQuantity(1);

            HttpSession session = request.getSession();
            ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart-list");

            if (cartList == null) {
              
                cartList = new ArrayList<>();
                cartList.add(cartItem);
                session.setAttribute("cart-list", cartList);
                message = "Item added to cart.";
            } else {

                boolean exists = false;

                for (Cart item : cartList) {
                    if (item.getProductId() == id) {
                        exists = true;
                        message = "Item already in cart.";
                        break;
                    }
                }

                if (!exists) {
                    cartList.add(cartItem);
                    message = "Item added to cart.";
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            message = "Invalid product ID.";
        }

        HttpSession session = request.getSession();
        session.setAttribute("popupMessage", message);

        String redirectTarget = request.getParameter("redirect");
        if (redirectTarget != null && redirectTarget.equals("products")) {
            response.sendRedirect("products.jsp");
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}
