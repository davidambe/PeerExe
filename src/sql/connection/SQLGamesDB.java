package sql.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLGamesDB {

	private static String JDBC_DRIVER;
	private static String DB_URL;
	private static String USER;
	private static String PASS;

	Connection conn = null;
	Statement stmt = null;

	public Connection getConnection() {
		return conn;
	}

	public SQLGamesDB() {
		this.JDBC_DRIVER = "com.mysql.jdbc.Driver";
		this.DB_URL = "jdbc:mysql://localhost:3306/customerdb?useSSL=false";
		this.USER = "root";
		this.PASS = "root";

		// Establishing Connection
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Starting Database Connection...");
		// Connection Established
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Create a customer

	}

	public static void main(String[] args) {
		SQLGamesDB customer = new SQLGamesDB();
		Connection conn = customer.getConnection();
		Customer cm1 = new Customer();
		

		cm1.createCustomer(conn, "James", "Bond", 012335, "This Road", "BM13UB32", "United Kingdom", "London",
				"customer");
		cm1.selectCustomer(conn, 2, "customer");
	
	}

}