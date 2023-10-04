package javaProject8;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BooksDAO {

    // BooksService showAllBooks에서 호출 받아 DB에서 모든 책 가져오기
    public List<BooksVO> getAllBooks() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<BooksVO> vos = new ArrayList<>();

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
    
    // BooksService searchBooks에서 호출 받아 DB에서 개별 책 가져오기
    public List<BooksVO> searchBooks(String selectedColumn, String keyword) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<BooksVO> vos = new ArrayList<>();
        
        try {
            conn = DatabaseConnector.getConnection();

            String sql = "SELECT * FROM books WHERE " + selectedColumn + " LIKE ?";            
            pstmt = conn.prepareStatement(sql);
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

    // BooksService searchBooksWithBoolean에서 호출 받아 DB에서 개별 책 가져오기(boolean)
    public List<BooksVO> searchBooksWithBoolean(String selectedColumn, boolean booleanKeyword) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<BooksVO> vos = new ArrayList<>();
        
        try {
            conn = DatabaseConnector.getConnection();
            String sql = "SELECT * FROM books WHERE " + selectedColumn + " LIKE ?";            
            pstmt = conn.prepareStatement(sql);
            pstmt.setBoolean(1, booleanKeyword);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BooksVO vo = new BooksVO();
                vo.setBookID(rs.getInt("bookID"));
                vo.setTitle(rs.getString("title"));
                vo.setAuthor(rs.getString("author"));
                vo.setPublisher(rs.getString("publisher"));
                vo.setGenre(rs.getString("genre"));
                vo.setAvailable(rs.getBoolean("isAvailable"));
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

    // 대출 가능 여부가 true인 책을 대출을 하면 false로 바꿔주는 메소드
    public void availableControl(int bookID, boolean isAvailable) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnector.getConnection();
            String sql = "UPDATE books SET isAvailable = NOT ? WHERE bookID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setBoolean(1, isAvailable);
            pstmt.setInt(2, bookID);

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

    // 도서 추가 메소드
    public boolean addBook(BooksVO newBook) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnector.getConnection();
            String sql = "INSERT INTO books (title, author, publisher, genre, isAvailable) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newBook.getTitle());
            pstmt.setString(2, newBook.getAuthor());
            pstmt.setString(3, newBook.getPublisher());
            pstmt.setString(4, newBook.getGenre());
            pstmt.setBoolean(5, newBook.isAvailable());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("도서가 성공적으로 추가되었습니다.");
                return true;
            } else {
                System.out.println("도서 추가 중 오류가 발생했습니다.");
                return false;
            }
        } catch (Exception e) {
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
   
    // 도서 삭제 메소드
    public boolean deleteBook(int bookID) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnector.getConnection();
            String sql = "DELETE FROM books WHERE bookID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bookID);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("도서가 성공적으로 삭제되었습니다.");
                return true;
            } else {
                System.out.println("도서 삭제 중 오류가 발생했습니다.");
                return false;
            }
        } catch (Exception e) {
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}