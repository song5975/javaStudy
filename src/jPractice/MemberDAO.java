package jPractice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	
    // MemberService memberCheck에서 호출 받아 아이디, 비밀번호 DB에서 확인
    public static boolean memberCheck(String username, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        boolean isMember = false;

        try {
            conn = DatabaseConnector.getConnection();
            String sql = "SELECT * FROM members WHERE name = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                isMember = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) {
                    conn.close();
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
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
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
	            return rs.getInt("memberID");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return -1;
	}

	// 전체 회원 조회
	public List<MembersVO> getAllMembers() {
	    List<MembersVO> vos = new ArrayList<>();

	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        conn = DatabaseConnector.getConnection();
	        String sql = "SELECT * FROM members";
	        pstmt = conn.prepareStatement(sql);
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            MembersVO member = new MembersVO();
	            member.setMemberID(rs.getInt("memberID"));
	            member.setName(rs.getString("name"));
	            member.setPassword(rs.getString("password"));
	            member.setContact(rs.getString("contact"));
	            member.setAddress(rs.getString("address"));
	            vos.add(member);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return vos;
	}

	// 신규회원 추가
	public boolean updateMember(int memberID, String newName, String newPassword, String newContact, String newAddress) {
		
    	System.out.println("update_debug: " + newName + newPassword + newContact + newAddress);

		
	    Connection conn = null;
	    PreparedStatement pstmt = null;

	    try {
	        conn = DatabaseConnector.getConnection();
	        String sql = "UPDATE members SET name = ?, password = ?, contact = ?, address = ? WHERE memberID = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, newName);
	        pstmt.setString(2, newPassword);
	        pstmt.setString(3, newContact);
	        pstmt.setString(4, newAddress);
	        pstmt.setInt(5, memberID);

	        int rowCount = pstmt.executeUpdate();

	        if (rowCount > 0) {
	            return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (pstmt != null) pstmt.close();
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return false;
	}

    // 회원 정보 삭제(회원 테이블)
	public boolean deleteMember(int memberID) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;

		System.out.println("Debug1 : " + memberID);
	    
	    try {
	        conn = DatabaseConnector.getConnection();
	        String sql = "DELETE FROM members WHERE memberID = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, memberID);

	        int rowsAffected = pstmt.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("회원이 성공적으로 삭제되었습니다.");
	            return true;
	        } else {
	            System.out.println("회원 삭제 중 오류가 발생했습니다.");
	            return false;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            if (pstmt != null) {
	                pstmt.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

}