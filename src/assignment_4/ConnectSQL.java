package assignment_4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectSQL {
	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/mysql";
		String user = "root";
		String pass = "";

		try {

			// Attempt Connection
			Connection conn = DriverManager.getConnection(url, user, pass);

			if (conn != null) {
				System.out.println("Connection Successful");
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("Unable to connect");
			e.printStackTrace();
		}
	}
}