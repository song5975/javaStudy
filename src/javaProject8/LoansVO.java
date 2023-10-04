package javaProject8;

public class LoansVO {
	private int loanID;
	private int memberID;
	private int bookID;
	private String loanDate;
	private String returnDate;
	
	int getLoanID() {
		return loanID;
	}
	void setLoanID(int loanID) {
		this.loanID = loanID;
	}
	int getMemberID() {
		return memberID;
	}
	void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	int getBookID() {
		return bookID;
	}
	void setBookID(int bookID) {
		this.bookID = bookID;
	}
	String getLoanDate() {
		return loanDate;
	}
	void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}
	String getReturnDate() {
		return returnDate;
	}
	void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	
	@Override
	public String toString() {
		return "LoansVO [loanID=" + loanID + ", memberID=" + memberID + ", bookID=" + bookID + ", loanDate=" + loanDate
				+ ", returnDate=" + returnDate + "]";
	}
}