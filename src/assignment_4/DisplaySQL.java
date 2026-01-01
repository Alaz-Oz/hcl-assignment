package assignment_4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DisplaySQL {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/mysql";
		String user = "root";
		String pass = "";

		String sql = "SELECT * FROM Students WHERE semester = 7 AND branch = 'EC'";

		try (Connection conn = DriverManager.getConnection(url, user, pass);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			System.out.println("--- Students (Sem 7, Branch EC) ---");
			System.out.printf("%-5s %-15s %-10s %-10s\n", "ID", "Name", "Branch", "Percentage");
			System.out.println("------------------------------------------------");

			boolean found = false;
			while (rs.next()) {
				found = true;
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String branch = rs.getString("branch");
				double per = rs.getDouble("percentage");

				System.out.printf("%-5d %-15s %-10s %-10.2f\n", id, name, branch, per);
			}

			if (!found) {
				System.out.println("No records found.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}