package javaProject8;

import java.util.List;

public class BooksService {

	// 도서 전체 검색
    public List<BooksVO> showAllBooks() {
        BooksDAO dao = new BooksDAO();
        List<BooksVO> vos = dao.getAllBooks();
        return vos;
    }

    // 도서 개별 검색(String)
    public List<BooksVO> searchBooks(String selectedColumn, String keyword) {
        BooksDAO dao = new BooksDAO();
        List<BooksVO> vos = dao.searchBooks(selectedColumn, keyword);
        return vos;
    }
   
    // 도서 개별 검색(boolean)
    public List<BooksVO> searchBooksWithBoolean(String selectedColumn, boolean booleanKeyword) {
    	BooksDAO dao = new BooksDAO();
    	List<BooksVO> vos = dao.searchBooksWithBoolean(selectedColumn, booleanKeyword);
    	return vos;
    }

    // 대출 가능 여부가 true인 책을 대출을 하면 false로 바꿔주는 메소드
    public void availableControl(int bookID, boolean isAvailable) {
        BooksDAO dao = new BooksDAO();
        dao.availableControl(bookID, isAvailable);
    }

    // 도서 추가 메소드(유효성 검사)
    public boolean addBook(BooksVO newBook) {
    	if (newBook.getTitle() == null || newBook.getTitle().equals("") ||
    		newBook.getAuthor() == null || newBook.getAuthor().equals("") ||
    		newBook.getPublisher() == null || newBook.getPublisher().equals("") ||
    		newBook.getGenre() == null || newBook.getGenre().equals("")) {
    		System.out.println("도서 정보를 모두 입력하세요.");
    		return false;
    		}

        // 제목과 작가명 길이
        int titleLength = newBook.getTitle().length();
        int authorLength = newBook.getAuthor().length();
        int maxTitleLength = 50; // 최대 도서 제목 길이
        int maxAuthorLength = 30; // 최대 작가 이름 길이

        if (titleLength > maxTitleLength || authorLength > maxAuthorLength) {
            System.out.println("도서 제목 또는 작가 이름이 너무 깁니다.");
            return false;
        }

        BooksDAO dao = new BooksDAO();
        boolean isSuccess = dao.addBook(newBook);

        return isSuccess;
    }

    // 도서 삭제 메소드
    public boolean deleteBook(int bookID) {
        BooksDAO dao = new BooksDAO();
        boolean isSuccess = dao.deleteBook(bookID);
        return isSuccess;
    }


}
