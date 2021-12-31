package passwordManager;

import java.sql.*;
import java.util.ArrayList;

public class MenuOptions {
	
	public static ArrayList<Password> getPasswords() {
		ArrayList<Password> passwords = new ArrayList<Password>();
		String getQuery = "SELECT passwordCred, usernameCred, url, passName FROM passwords;";
			
		try {
			Connection myDB = LocalDB.getConnection(null, null);
			PreparedStatement ps = myDB.prepareStatement(getQuery);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				String tempPassCred = rs.getString("passwordCred");
				String tempUserCred = rs.getString("usernameCred");
				String tempUrl = rs.getString("url");
				String tempPassName = rs.getString("passName");
				
				Password temp = new Password(tempPassCred, tempUserCred, tempUrl, tempPassName);
				passwords.add(temp);
			}
		} catch (Exception exc) {
			System.out.println(exc);
			
			passwords = null;
		}
		
		return(passwords);
	}
	
	public static void savePassword(Password toSave) {
		String insertQuery = "INSERT INTO passwords (passwordCred, usernameCred, url, passName) VALUES (?, ?, ?, ?);";
		
		try {
			Connection myDB = LocalDB.getConnection(null, null);
			PreparedStatement ps = myDB.prepareStatement(insertQuery);
			
			ps.setString(1, toSave.getPassword());
			ps.setString(2, toSave.getUsername());
			ps.setString(3, toSave.getUrl());
			ps.setString(4, toSave.getName());
			
			ps.executeUpdate();
		} catch (Exception exc) {
			System.out.println(exc);
		}
	}
	
	public static void clearPasswords() {
		String clearQuery = "DELETE FROM passwords";
		
		try {
			Connection myDB = LocalDB.getConnection(null, null);
			PreparedStatement ps = myDB.prepareStatement(clearQuery);
			
			ps.executeUpdate();
		} catch (Exception exc) {
			System.out.println(exc);
		}
	}
}
