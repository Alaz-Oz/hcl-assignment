package assignment_4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DeleteSQL {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/mysql";
		String user = "root";
		String pass = "";

		String sql = "DELETE FROM Students WHERE branch = 'Civil' AND pass_year = '2024-1-1'";

		try (Connection conn = DriverManager.getConnection(url, user, pass); Statement stmt = conn.createStatement()) {

			int rowsAffected = stmt.executeUpdate(sql);
			System.out.println("Deleted " + rowsAffected + " student(s) from Civil branch (2024 batch).");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}