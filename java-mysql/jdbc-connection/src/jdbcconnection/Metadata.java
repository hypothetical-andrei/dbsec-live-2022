package jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Metadata {

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
			ResultSet rs = stmt.executeQuery("select * from students");
			ResultSetMetaData rsmd = rs.getMetaData();
			System.out.println(rsmd.getCatalogName(1));
			System.out.println(rsmd.getSchemaName(1));
			System.out.println(rsmd.getTableName(1));
			int colNumber = rsmd.getColumnCount();
			for (int i = 1; i <= colNumber; i++) {
				System.out.println("Col : " + rsmd.getColumnName(i));
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
