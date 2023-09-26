package jPractice;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoansDAO {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public LoansDAO() {
        conn = DatabaseConnector.getConnection();
    }

    // pstmt객체 close
    public void pstmtClose() {
        try {
            if (pstmt != null)
                pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // rs객체 close
    public void rsClose() {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pstmtClose();
    }

    // 대출
    public void addNewLoans(int memberID, int bookID, Date loanDate, Date returnDate) {
        Connection conn = null;
        PreparedStatement pstmt = null;
	
        try {
            conn = DatabaseConnector.getConnection();
            String sql = "INSERT INTO loans VALUES (default, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberID);
            System.out.println("memberID " + memberID);
            pstmt.setInt(2, bookID);
            System.out.println("bookID " + bookID);
            pstmt.setDate(3, loanDate);
            pstmt.setDate(4, returnDate);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("대출 기록이 추가되었습니다.");
            } else {
                System.out.println("대출 기록 추가에 실패했습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 대출 기록 전체 조회
	public List<LoansVO> showAllLoans(int memberID) {
			System.out.println("LoansDAO: name = " + memberID);

		
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        List<LoansVO> vos = new ArrayList<>();

	        try {
	            conn = DatabaseConnector.getConnection();
	            String sql = "SELECT * FROM Loans WHERE memberID = ?";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, memberID);
	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	            	LoansVO vo = new LoansVO();
	            	vo.setLoanID(rs.getInt("loanID"));
	            	vo.setMemberID(rs.getInt("memberID"));
	            	vo.setBookID(rs.getInt("bookID"));
	            	vo.setLoanDate(rs.getString("loanDate"));
	                vo.setReturnDate(rs.getString("returnDate"));


	                vos.add(vo); //
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (rs != null) {
	                    rs.close();
	                }
	                if (pstmt != null) {
	                    pstmt.close();
	                }
	                if (conn != null) {
	                    conn.close();
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }

	        return vos;
	    }

	// 반납
	public void removeLoans(int loanID) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;

	    try {
	        conn = DatabaseConnector.getConnection();
	        String sql = "DELETE FROM loans WHERE loanID = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, loanID);
	        
	        int rowsAffected = pstmt.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            System.out.println("대출 기록이 삭제되었습니다.");
	        } else {
	            System.out.println("대출 기록 삭제에 실패했습니다.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (pstmt != null) {
	                pstmt.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

	// 대출 ID로 대출 정보 조회
	public LoansVO getLoanByID(int loanID) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    LoansVO loansVO = null;

	    try {
	        conn = DatabaseConnector.getConnection();
	        String sql = "SELECT * FROM Loans WHERE loanID = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, loanID);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            loansVO = new LoansVO();
	            loansVO.setLoanID(rs.getInt("loanID"));
	            loansVO.setMemberID(rs.getInt("memberID"));
	            loansVO.setBookID(rs.getInt("bookID"));
	            loansVO.setLoanDate(rs.getString("loanDate"));
	            loansVO.setReturnDate(rs.getString("returnDate"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (pstmt != null) {
	                pstmt.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return loansVO;
	}

	// 관리자용 전체 대출 조회
	public List<LoansVO> showAdminLoans() {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    List<LoansVO> vos = new ArrayList<>();

	    try {
	        conn = DatabaseConnector.getConnection();
	        String sql = "SELECT * FROM Loans";
	        pstmt = conn.prepareStatement(sql);
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            LoansVO vo = new LoansVO();
	            vo.setLoanID(rs.getInt("loanID"));
	            vo.setMemberID(rs.getInt("memberID"));
	            vo.setBookID(rs.getInt("bookID"));
	            vo.setLoanDate(rs.getString("loanDate"));
	            vo.setReturnDate(rs.getString("returnDate"));

	            vos.add(vo);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (pstmt != null) {
	                pstmt.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return vos;
	}

}

