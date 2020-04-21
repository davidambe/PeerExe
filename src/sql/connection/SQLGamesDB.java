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
		this.JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		this.DB_URL = "jdbc:mysql://localhost:3306/gamedb?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
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

		//cm1.createCustomer(conn, "James", "Tom@gmail.com", "This Road", "password", "customers");
		//cm1.createCustomer(conn, "Tom", "Tom@gmail.com", "This Road", "password", "customers");
		//cm1.selectCustomer(conn, 2, "customers");
		
		Product p = new Product();
		//p.addProduct(conn, "T-shirt", 1000, 44.44);
		//p.addProduct(conn, "Jacket", 2000, 54.44);
		
		//p.deleteProduct(conn, 1);
		//p.updateProduct(conn, "Shirt", 1);
		
		Orders order = new Orders();
		order.createOrder(conn, 8, 4);
		
		order.selectOrder(conn, 8, 1);
				
		
		
		
	
	}

}