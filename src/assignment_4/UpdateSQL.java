package assignment_4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UpdateSQL {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/mysql";
		String user = "root";
		String pass = "";

		String sql = "UPDATE Students SET percentage = percentage + 5 WHERE branch = 'CSE'";

		try (Connection conn = DriverManager.getConnection(url, user, pass); Statement stmt = conn.createStatement()) {

			int rowsAffected = stmt.executeUpdate(sql);
			System.out.println(rowsAffected + " CSE students' records updated (percentage increased by 5).");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}