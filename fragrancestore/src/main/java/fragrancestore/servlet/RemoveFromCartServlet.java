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

@WebServlet("/remove-from-cart")
public class RemoveFromCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RemoveFromCartServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));

        HttpSession session = request.getSession();
        ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart-list");

        if (cartList != null) {
            cartList.removeIf(cartItem -> cartItem.getProductId() == productId);

            session.setAttribute("cart-list", cartList);

            if (cartList.isEmpty()) {
                session.setAttribute("popupMessage", "Cart cleared");
                response.sendRedirect("index.jsp");
            } else {
                session.setAttribute("popupMessage", "Item removed successfully");
                response.sendRedirect("cart.jsp");
            }
        } else {
            response.sendRedirect("cart.jsp");
        }
    }
}
