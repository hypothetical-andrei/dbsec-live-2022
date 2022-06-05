package jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionTest {

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
			conn.setAutoCommit(false);
			PreparedStatement pstmt1 =conn.prepareStatement("insert into students values (?,?,?,?)");
			pstmt1.setInt(1, 11);
			pstmt1.setString(2, "someone");
			pstmt1.setString(3, "444");
			pstmt1.setInt(4, 1);
			PreparedStatement pstmt2 =conn.prepareStatement("update students set telephone = ? where student_id = ?");
			pstmt2.setString(1, "1-1-1-1");
			pstmt2.setInt(2, 11);
			pstmt1.executeUpdate();
			pstmt2.executeUpdate();
//			conn.commit();
			conn.rollback();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

}
