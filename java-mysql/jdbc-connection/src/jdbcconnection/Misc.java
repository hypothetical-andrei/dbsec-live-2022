package jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Misc {

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String connectionString = "jdbc:mysql://localhost/?user=app1&password=welcome123";
//		String user = "app1";
//		String password = "welcome123";
		try {
			conn = DriverManager.getConnection(connectionString);
			Statement stmt = conn.createStatement();
			int result = stmt.executeUpdate("create database if not exists ismv4");
			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		connectionString = "jdbc:mysql://localhost/ismv4?user=app1&password=welcome123";
		try {
			conn = DriverManager.getConnection(connectionString);
			Statement stmt = conn.createStatement();
			int result = stmt.executeUpdate("create table if not exists test (id integer not null primary key, content varchar(100))");
			System.out.println(result);
            PreparedStatement pstmt = conn.prepareStatement("insert into test (id, content) values (?,?)");
            pstmt.setInt(1, 1);
            pstmt.setString(2, "aaa");
            pstmt.addBatch();
            pstmt.setInt(1, 2);
            pstmt.setString(2, "bbb");
            pstmt.addBatch();      
            pstmt.executeBatch();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from test");
            while (rs.next()) {
				String content = rs.getString(2);
				rs.updateString(2, content.toUpperCase());
				rs.updateRow();
			}
            rs.beforeFirst();
            while (rs.next()) {
				System.out.println(rs.getString("content"));
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
