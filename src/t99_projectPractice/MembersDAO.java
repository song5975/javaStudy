package t99_projectPractice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MembersDAO {
    // 다른 멤버 변수 및 메소드 정의

	// 관리자 아이디 확인
    public static boolean isAdminID(String userID) {
        boolean isAdmin = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnector.getConnection(); // 데이터베이스 연결
            String sql = "SELECT * FROM members WHERE memberID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userID);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String adminID = rs.getString("memberID");
                if ("ADMIN".equals(adminID)) {
                    isAdmin = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) {
                    conn.close(); // 데이터베이스 연결 종료
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isAdmin;
    }
    
	// 관리자 비밀번호 확인
    public static boolean isAdminPassword(String password) {
        boolean isAdmin = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnector.getConnection(); // 데이터베이스 연결
            String sql = "SELECT * FROM members WHERE memberID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "ADMIN");
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                if (password.equals(storedPassword)) {
                    isAdmin = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) {
                    conn.close(); // 데이터베이스 연결 종료
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isAdmin;
    }
    
    // 회원 아이디 확인
	public static Object ismemberID(String userID) {
		boolean ismember = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnector.getConnection(); // 데이터베이스 연결
            String sql = "SELECT * FROM members WHERE memberID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userID);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String memberID = rs.getString("memberID");
                if ((memberID != null)) {
                	ismember = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) {
                    conn.close(); // 데이터베이스 연결 종료
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		return ismember;
	}

	// 회원 비밀번호 확인
	public static boolean ismemberPassword(String userID, String password) {
	    boolean ismember = false;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        conn = DatabaseConnector.getConnection(); // 데이터베이스 연결
	        String sql = "SELECT * FROM members WHERE memberID = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, userID);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            String storedPassword = rs.getString("password");
	            if (password.equals(storedPassword)) {
	                ismember = true;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            if (conn != null) {
	                conn.close(); // 데이터베이스 연결 종료
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return ismember;
	}

	



    // 다른 메소드 및 필드 정의
}
