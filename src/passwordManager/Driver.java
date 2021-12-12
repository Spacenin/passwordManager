package passwordManager;

import java.sql.*;
import gui.*;

public class Driver {

	public static void main(String[] args) {		
		new Login();
		
		LocalDB.closeConnection();
	}

}
