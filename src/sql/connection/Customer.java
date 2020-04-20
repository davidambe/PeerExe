package sql.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Customer {
	// CRUD Create, Read, Update, Delete
	Connection conn = null;
	Statement stmt = null;

	public void createCustomer(Connection conn, String firstName, String lastName, long phone_number, String Address,
			String post_code, String country, String Region, String tableName) {

		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// variable with a sql statement as the value!

		String sqlInsert = "INSERT INTO " + tableName
				+ " (firstName, lastName, phone_number, Address, post_code, country, region) VALUES('" + firstName
				+ "','" + lastName + "','" + phone_number + "','" + Address + "','" + post_code + "','" + country
				+ "','" + Region + "')";
		try {
			stmt.executeUpdate(sqlInsert);
			System.out.println("Record inserted!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateCustomer(Connection conn, String firstName, int CustomerID, String tableName) {
		// Update Customer
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Update Table");
		String sql3 = "update " + tableName + " set firstName = '" + firstName + "' where CustomerID= '" + CustomerID
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
		String sql2 = "SELECT CustomerID,firstName,lastName,phone_number,Address,post_code,country,region from "
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
				String firstName1 = rs.getString("firstName");
				String lastName1 = rs.getString("lastName");
				String Address1 = rs.getString("Address");
				System.out.println("CustomerID: " + CustomerID1 + " First Name: " + firstName1 + " Last Name: " + lastName1
						+ "Address:" + Address1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}