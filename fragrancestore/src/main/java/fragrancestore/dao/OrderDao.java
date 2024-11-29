package fragrancestore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fragrancestore.model.Order;
import com.fragrancestore.model.Product;

public class OrderDao {
    private Connection con;

    public OrderDao(Connection con) {
        this.con = con;
    }

    public boolean insertOrder(Order order) {
        String query = "INSERT INTO orders (user_id, order_quantity, order_date, total_price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, order.getUserId());
            pst.setInt(2, order.getOrderQuantity());
            pst.setString(3, order.getOrderDate());
            pst.setDouble(4, order.getTotalPrice());
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Order> getOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE user_id = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setOrderQuantity(rs.getInt("order_quantity"));
                order.setOrderDate(rs.getString("order_date"));
                order.setTotalPrice(rs.getDouble("total_price"));
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    public boolean cancelOrder(int orderId) {
        String query = "DELETE FROM orders WHERE order_id = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, orderId);
            int rowsDeleted = pst.executeUpdate();
            return rowsDeleted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
