package assignment_4;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class CreateTable {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/mysql";
		String user = "root";
		String pass = "";

		String createTableSQL = "CREATE TABLE IF NOT EXISTS Students (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), branch VARCHAR(20), percentage DOUBLE, pass_year DATE, semester INT)";

		String insertSQL = "INSERT INTO Students VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = DriverManager.getConnection(url, user, pass); Statement stmt = conn.createStatement()) {

			// Create Table
			stmt.execute(createTableSQL);
			System.out.println("Table 'Students' created successfully.");

			// Insert Records using PreparedStatement
			PreparedStatement pstmt = conn.prepareStatement(insertSQL);

			// ID, Name, Branch, Percentage, Year, Semester
			Object[][] data = { { 101, "Afroz", "CSE", 75.0, 2025, 5 }, { 102, "Muskan", "CSE", 82.0, 2025, 5 },
					{ 103, "Raghav", "Civil", 65.0, 2024, 8 }, { 104, "Piyush", "Civil", 70.0, 2024, 8 }, };

			for (Object[] row : data) {
				pstmt.setInt(1, (Integer) row[0]);
				pstmt.setString(2, (String) row[1]);
				pstmt.setString(3, (String) row[2]);
				pstmt.setDouble(4, (Double) row[3]);
				pstmt.setDate(5, Date.valueOf(row[4] + "-1-1"));
				pstmt.setInt(6, (Integer) row[5]);
				pstmt.executeUpdate();
			}

			System.out.println("Records inserted successfully.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}