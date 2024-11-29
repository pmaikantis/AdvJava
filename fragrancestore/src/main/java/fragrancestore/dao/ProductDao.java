package fragrancestore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fragrancestore.model.Cart;
import com.fragrancestore.model.Product;

public class ProductDao {
    private Connection con;

    public ProductDao(Connection con) {
        this.con = con;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = """
            SELECT 
                p.fragrance_id, 
                c.category_name AS category, 
                p.title, 
                p.perfumer, 
                p.description, 
                p.notes, 
                p.price, 
                p.release_date, 
                p.image 
            FROM products p
            JOIN categories c ON p.category_id = c.category_id
        """;
        try (PreparedStatement pst = this.con.prepareStatement(query); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Product product = extractProductFromResultSet(rs);
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> getProductsByOrderId(int orderId) {
        List<Product> products = new ArrayList<>();
        String query = """
            SELECT p.*
            FROM products p
            JOIN order_products op ON p.fragrance_id = op.product_id
            WHERE op.order_id = ?
        """;
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, orderId);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Product product = extractProductFromResultSet(rs);
                    products.add(product);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
        List<Cart> products = new ArrayList<>();
        String query;
        try {
            if (cartList != null && !cartList.isEmpty()) {
                for (Cart item : cartList) {
                    query = """
                        SELECT fragrance_id, title, perfumer, price, image 
                        FROM products 
                        WHERE fragrance_id = ?
                    """;
                    try (PreparedStatement pst = this.con.prepareStatement(query)) {
                        pst.setInt(1, item.getProductId());
                        try (ResultSet rs = pst.executeQuery()) {
                            if (rs.next()) {
                                Cart row = new Cart();
                                row.setProductId(rs.getInt("fragrance_id"));
                                row.setTitle(rs.getString("title"));
                                row.setPerfumer(rs.getString("perfumer"));
                                row.setPrice(rs.getDouble("price"));
                                row.setQuantity(item.getQuantity());
                                row.setImage(rs.getString("image")); // Set the image for thumbnails
                                products.add(row);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error fetching cart products: " + e.getMessage());
        }
        return products;
    }

    public Product getProductById(int productId) {
        Product product = null;
        String query = "SELECT * FROM products WHERE fragrance_id = ?";
        try (PreparedStatement pst = this.con.prepareStatement(query)) {
            pst.setInt(1, productId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    product = extractProductFromResultSet(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    private Product extractProductFromResultSet(ResultSet rs) throws Exception {
        Product product = new Product();
        product.setFragranceId(rs.getInt("fragrance_id"));
        product.setCategory(rs.getString("category")); // Fetch category_name
        product.setTitle(rs.getString("title"));
        product.setPerfumer(rs.getString("perfumer"));
        product.setDescription(rs.getString("description"));
        product.setNotes(rs.getString("notes"));
        product.setPrice(rs.getDouble("price"));
        product.setReleaseDate(rs.getDate("release_date"));
        product.setImage(rs.getString("image")); // Fetch and set the image
        return product;
    }
}
