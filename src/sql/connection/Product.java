package sql.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Product {
	
	// CRUD Create, Read, Update, Delete
		Connection conn = null;
		PreparedStatement ps = null;
		
		private final String ADD_NEW_PRODUCT_SQL = "INSERT INTO Products " + "(productName, stock, price) VALUES " +
		           "(?,?,?);";     
		
		private final String UPDATE_PRODUCT_SQL = "UPDATE products SET productName = ? WHERE Product_ID = ?";
		
		private final String DELETE_PRODUCT_SQL = "DELETE from products WHERE Product_ID = ?";
		
		

		public void addProduct(Connection conn, String productName, int stock, double price) {
			
			try {
				ps = conn.prepareStatement(ADD_NEW_PRODUCT_SQL);
				ps.setString(1, productName);
				ps.setInt(2, stock);
				ps.setDouble(3, price);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		public void updateProduct(Connection conn, String productName, int productId) {
			
			try {
				ps = conn.prepareStatement(UPDATE_PRODUCT_SQL);
				ps.setString(1, productName);
				ps.setInt(2, productId);
	
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		public void deleteProduct(Connection conn, int productId) {
			
			try {
				ps = conn.prepareStatement(DELETE_PRODUCT_SQL);
				ps.setInt(1, productId);
	
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

}
