package t99_projectPractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/javaProject";
    private static final String USER = "atom";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() {
    	Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패~~" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("데이터베이스 연동 실패~~" + e.getMessage());
		}
        return conn;
    }
}