package jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Batching {

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:mysql://localhost:3306/ismv3";
		String user = "app1";
		String password = "welcome123";
		try {
			conn = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = conn.prepareStatement("insert into simpleauto (content) values (?)",
					Statement.RETURN_GENERATED_KEYS);
			for (int i = 0; i < 10; i++) {
				pstmt.setString(1, "string " + i);
				pstmt.addBatch();
			}
			int[] results = pstmt.executeBatch();
			System.out.println("Op results");
			for (int i = 0; i < results.length; i++) {
				System.out.println(results[i]);
			}
			System.out.println("Keys");
			ResultSet rs = pstmt.getGeneratedKeys();
			while (rs.next()) {
				System.out.println(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

}
