package sql.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Customer {
	// CRUD Create, Read, Update, Delete
	Connection conn = null;
	Statement stmt = null;

	public void createCustomer(Connection conn, String name, String email, String Address,
			String password, String tableName) {

		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// variable with a sql statement as the value!

		String sqlInsert = "INSERT INTO " + tableName
				+ " (name, Address, email, password) VALUES('" + name
				+ "','" + email + "','" + Address + "','" + password + "')";
		try {
			stmt.executeUpdate(sqlInsert);
			System.out.println("Record inserted!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateCustomer(Connection conn, int customerID, String name, String tableName) {
		// Update Customer
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Update Table");
		String sql3 = "update " + tableName + " set name = '" + name + "' where CustomerID= '" + customerID
				+ "';";
		try {
			stmt.executeUpdate(sql3);
			System.out.println("Record has now been updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("OH no big error!!!");
			e.printStackTrace();
		}
	}

	public void selectCustomer(Connection conn, int CustomerID, String tableName) {
		// Select Statement
		System.out.println("Creating select statement");
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql2 = "SELECT * from "
				+ tableName + " WHERE CustomerID = '"+ CustomerID+ "';";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql2);
			// System.out.println("what does RS print?"+rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				int CustomerID1 = rs.getInt("CustomerID");
				String firstName1 = rs.getString("name");
				String lastName1 = rs.getString("address");

				System.out.println("CustomerID: " + CustomerID1 + " First Name: " + firstName1 + " Last Name: " + lastName1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}