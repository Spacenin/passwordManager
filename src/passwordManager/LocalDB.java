package passwordManager;

import java.sql.*;

public class LocalDB {
	private static Connection myDB;
	
	public static Connection getConnection(String username, String password) {
		if (myDB != null) {
			return(myDB);
		}
		
		else {
			try {	
				String url = "jdbc:mysql://localhost/data1";
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				myDB = DriverManager.getConnection(url, username, password);
			} catch (Exception exc) {
				System.out.println(exc);
				
				return(null);
			}
			
			System.out.println("Connected!");
			
			createTable();
			return(myDB);
		}
	}
	
	public static void closeConnection() {
		if (myDB != null) {
			try {
				myDB.close();
			} catch (Exception exc) {
				System.out.println(exc);
			}
		}
	}
	
	public static void createTable() {
		String createQuery = "CREATE TABLE IF NOT EXISTS passwords (\r\n"
				+ "	id int AUTO_INCREMENT UNIQUE,\r\n"
				+ "    passwordCred varchar(255),\r\n"
				+ "    usernameCred varchar(255),\r\n"
				+ "    url varchar(100),\r\n"
				+ "    passName varchar(100),\r\n"
				+ "    PRIMARY KEY(id)\r\n"
				+ ");";
		
		try {
			PreparedStatement ps = myDB.prepareStatement(createQuery);
			
			ps.executeUpdate();
		} catch (Exception exc) {
			System.out.println(exc);
		}
	}
}
