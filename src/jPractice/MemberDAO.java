package jPractice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {
	
    // MemberService memberCheck에서 호출 받아 아이디, 비밀번호 DB에서 확인
    public static boolean memberCheck(String username, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        boolean isMember = false; // 회원 여부를 나타내는 변수

        try {
            conn = DatabaseConnector.getConnection();
            String sql = "SELECT * FROM members WHERE name = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // 회원 정보가 일치하는 경우
                isMember = true;
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
        return isMember;
    }

    // MemberService addMember에서 값을 받아 DB에 새로운 회원을 등록
	public boolean addMember(String name, String password, String contact, String address) {
		Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnector.getConnection();
            String sql = "INSERT INTO members VALUES (default, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            pstmt.setString(3, contact);
            pstmt.setString(4, address);

            int rowCount = pstmt.executeUpdate(); // executeUpdate는 삽입된 행의 수를 반환

            if (rowCount > 0) {
                // 멤버 추가 성공
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) {
                    conn.close(); // 데이터베이스 연결 종료
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false; // 멤버 추가 실패
    }

	// 현재 사용자의 이름을 받아서 ID를 반환.
	public int getCurrentUserID(String name) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        conn = DatabaseConnector.getConnection();
	        String sql = "SELECT memberID FROM members WHERE name = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, name);
	        rs = pstmt.executeQuery();
            System.out.println("MemberDAO: name = " + name);

	        if (rs.next()) {
	            // 현재 사용자의 ID를 반환
	            return rs.getInt("memberID");
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

	    // 사용자가 없거나 오류 발생 시 -1을 반환
	    return -1;
	}

}