package jPractice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BooksDAO {

    // BooksService getAllBooks에서 호출 받아 DB에서 모든 책 가져오기
    public List<BooksVO> getAllBooks() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<BooksVO> vos = new ArrayList<>(); // BooksVO 객체를 저장할 리스트

        try {
            conn = DatabaseConnector.getConnection();
            String sql = "SELECT * FROM books";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BooksVO vo = new BooksVO();
                vo.setBookID(rs.getInt("bookID"));
                vo.setTitle(rs.getString("title"));
                vo.setAuthor(rs.getString("author"));
                vo.setPublisher(rs.getString("publisher"));
                vo.setGenre(rs.getString("genre"));
                vo.setAvailable(rs.getBoolean("isAvailable"));

                vos.add(vo); // 가져온 도서 정보를 리스트에 추가
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

        return vos; // 도서 정보를 담은 리스트를 반환
    }
    
    // BooksService searchBooks에서 호출 받아 DB에서 개별 책 가져오기
    public List<BooksVO> searchBooks(String selectedColumn, String keyword) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<BooksVO> vos = new ArrayList<>(); // 검색 결과를 저장할 리스트

        try {
            conn = DatabaseConnector.getConnection();

            String sql = "SELECT * FROM books WHERE `" + selectedColumn + "` LIKE ?";            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + keyword + "%");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BooksVO vo = new BooksVO();
                vo.setBookID(rs.getInt("bookID"));
                vo.setTitle(rs.getString("title"));
                vo.setAuthor(rs.getString("author"));
                vo.setPublisher(rs.getString("publisher"));
                vo.setGenre(rs.getString("genre"));
                vo.setAvailable(rs.getBoolean("isAvailable"));

                vos.add(vo); // 검색 결과를 리스트에 추가
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

        return vos; // 검색 결과를 담은 리스트를 반환
    }

    public void availableControl(int bookID, boolean isAvailable) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnector.getConnection();
            String sql = "UPDATE books SET isAvailable = false WHERE bookID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bookID);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("도서의 대출 가능 여부가 확인되었습니다.");
            } else {
                System.out.println("도서의 대출 가능 여부 확인에 실패했습니다.");
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

}