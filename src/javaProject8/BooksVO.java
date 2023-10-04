package javaProject8;

public class BooksVO {
	private int bookID;
	private String title;
	private String author;
	private String publisher;
	private String genre;
	private boolean isAvailable;
	
	int getBookID() {
		return bookID;
	}
	void setBookID(int bookID) {
		this.bookID = bookID;
	}
	String getTitle() {
		return title;
	}
	void setTitle(String title) {
		this.title = title;
	}
	String getAuthor() {
		return author;
	}
	void setAuthor(String author) {
		this.author = author;
	}
	String getPublisher() {
		return publisher;
	}
	void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	String getGenre() {
		return genre;
	}
	void setGenre(String genre) {
		this.genre = genre;
	}
	boolean isAvailable() {
		return isAvailable;
	}
	void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	@Override
	public String toString() {
		return "BooksVO [bookID=" + bookID + ", title=" + title + ", author=" + author + ", publisher=" + publisher
				+ ", genre=" + genre + ", isAvailable=" + isAvailable + "]";
	}
}
