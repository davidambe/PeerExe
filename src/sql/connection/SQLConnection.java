package sql.connection;

import java.sql.*;

public class SQLConnection {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/customerdb?useSSL=false";
	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Starting Database Connection...");

		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//
		int CustomerID = 0;
		String First_Name = "'customer1'";
		String Last_Name = "'customer1@email.com'";
		int Phone_number = 0;
		String Address = "This address";
		String Post_code = "'WM15 51CD'";
		String Country = "'United Kingdom'";
		String Region = "'London'";

		String tableName = "customer";
		System.out.println("Starting insert statement");
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// variable with a sql statement as the value!
		String sqlInsert = "INSERT INTO " + tableName + " VALUES(" + CustomerID + "," + First_Name + "," + Last_Name
				+ "," + Phone_number + "," + Address + "," + Post_code + "," + Country + "," + Region + ")";
		try {
			stmt.executeUpdate(sqlInsert);
			System.out.println("Record inserted!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Select Statement
		System.out.println("Creating select statement");
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String sql2="SELECT CustomerID,First_Name,Last_Name,Phone_number,Address,Post_code,Country,Region from "+tableName;
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql2);
            //System.out.println("what does RS print?"+rs);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            while(rs.next()) {
                int CustomerID1 = rs.getInt("CustomerID");
                String First_Name1 = rs.getString("First_Name");
                String Last_Name1 = rs.getString("Last_Name");
                String Address1 = rs.getString("Address");
                System.out.println("CustomerID:"+CustomerID1+"First Name:"+First_Name1+"Last Name:"+Last_Name1+"Address:"+Address1);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        //Update    
        System.out.println("Update Table");
        String sql3 = "update "+tableName+ " set First_Name=Kola where CustomerID= 14;";
        try {
            stmt.executeUpdate(sql3);
            System.out.println("Record has now been updated");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("OH no big error!!!");
            e.printStackTrace();
        }
        //Delete Record
        System.out.println("Delete record");
        String sql14 = "DELETE FROM customer WHERE CustomerID = 2";
        try {
			stmt.executeUpdate(sql14);
			System.out.println("Record has been deleted from the Database: "+ tableName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}