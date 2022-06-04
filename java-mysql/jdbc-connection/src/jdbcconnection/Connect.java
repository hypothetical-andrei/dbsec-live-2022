package jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

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
			if (!conn.isClosed()) {
				System.out.println("Connection is open");
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
