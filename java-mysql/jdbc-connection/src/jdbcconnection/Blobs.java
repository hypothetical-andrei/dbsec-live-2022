package jdbcconnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Blobs {

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
			PreparedStatement pstmt = conn.prepareStatement("insert into simpleblobs values (?, ?)");
			pstmt.setInt(1, 1);
			File file = new File("test.jpeg");
			FileInputStream inputStream = new FileInputStream(file);
			pstmt.setBinaryStream(2, inputStream, file.length());
			int result = pstmt.executeUpdate();
			System.out.println(result);
			pstmt = conn.prepareStatement("select content from simpleblobs");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				file = new File("out.jpeg");
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				InputStream reader = rs.getBinaryStream(1);
				byte[] buffer = new byte[1024];
				while (true) {
					int readBytes = reader.read(buffer);
					if (readBytes == 0) {
						break;
					}
					fileOutputStream.write(buffer, 0, readBytes);
				}
				fileOutputStream.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

}
