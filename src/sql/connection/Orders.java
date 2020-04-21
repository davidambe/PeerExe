package sql.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Orders {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	private static final String SELECT_COUNT_SQL = "SELECT COUNT(*) AS total from Orders WHERE fk_customerID = ? "
			+ "AND fk_productID = ?;";
	
	private final String ADD_TO_NEW_ORDER_SQL = "INSERT INTO Orders " + "(fk_customerID, fk_productID, quantity, placed) VALUES " +
	           "(?,?,1, curdate());";     
	
	private final String UPDATE_ORDER_QUANTITY_SQL = "UPDATE Orders SET quantity = quantity + 1 " +
			"WHERE fk_customerID = ? AND fk_productID = ?";
	
	private final String SELECT_ORDER_SQL = "SELECT p.productName, p.price, o.quantity FROM Orders as o JOIN products p" +
			" WHERE fk_customerID = ?";
	
	//SELECT count(*) from city as ct JOIN country cn ON ct.CountryCode=cn.Code WHERE cn.Code = 'USA';
	
	// CRUD Create, Read, Update, Delete
		
		
		public void createOrder(Connection conn, int customerId, int productId) { 
			int rowSelected = 0;
			
			try {
				ps = conn.prepareStatement(SELECT_COUNT_SQL);
				ps.setInt(1, customerId);
				ps.setInt(2, productId);

				rs = ps.executeQuery();

				if (rs.next()) {
					rowSelected = rs.getInt("total");
				}

				ps.close();
				if (rowSelected == 0) {
					
					try {
						ps = conn.prepareStatement(ADD_TO_NEW_ORDER_SQL);
						ps.setInt(1, customerId);
						ps.setInt(2, productId);
						ps.executeUpdate();

					} catch (SQLException e) {
						e.printStackTrace();
					}

				} else {
					try {
						ps = conn.prepareStatement(UPDATE_ORDER_QUANTITY_SQL);
						ps.setInt(1, customerId);
						ps.setInt(2, productId);	
						ps.executeUpdate();
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
				System.out.println("Item Added");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void selectOrder(Connection conn, int customerId, int orderId) {
		
			try {
				
				ps = conn.prepareStatement(SELECT_ORDER_SQL);
				ps.setInt(1, customerId);

				rs = ps.executeQuery();
				
				String productName = "";
                String price = "";
                String quantity = "";
                //String total = "";

	            while(rs.next()) {
	                productName = rs.getString("productName");
	                price = rs.getString("price");
	                quantity = rs.getString("quantity");
	                //total = rs.getString("total");
	                
	            }
	            System.out.println("Name: " + productName + " price "+ price + " quantity: " + quantity);
	            
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
		}
	

}
