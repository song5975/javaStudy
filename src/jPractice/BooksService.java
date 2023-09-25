package jPractice;

import java.util.List;

public class BooksService {

	public List<BooksVO> showAllBooks() {
        BooksDAO dao = new BooksDAO();
        List<BooksVO> vos = dao.getAllBooks();
        return vos;
    }

	public List<BooksVO> searchBooks(String selectedColumn, String keyword) {
        BooksDAO dao = new BooksDAO();
        List<BooksVO> vos = dao.searchBooks(selectedColumn, keyword);
        return vos;
    }

	 public void availableControl(int bookID, boolean isAvailable) {
	        BooksDAO dao = new BooksDAO();
	        dao.availableControl(bookID, isAvailable);
    }
}