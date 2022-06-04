package jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Injection {

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:mysql://localhost:3306/ismv3";
//		String odbcUrl = "jdbc:mysql:x";
		String user = "app1";
		String password = "welcome123";
		try {
			conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			String query = "select * from students where id = ";
			String id = "2";
			ResultSet rs = stmt.executeQuery(query + id);
			while (rs.next()) {
				System.out.println("Id: " + rs.getInt(1) + " Name: " + rs.getString("name") + " Phone: " + rs.getString(3));				
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
