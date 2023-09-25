package jPractice;

import java.sql.Date;
import java.util.List;

public class LoansService {

	// 전체 대출 조회
	public List<LoansVO> showAllLoans(int memberID) {
        System.out.println("LoansService: name = " + memberID);

		LoansDAO dao = new LoansDAO();
        List<LoansVO> vos = dao.showAllLoans(memberID);
		return vos;
	}
	
	// 대출 추가
	public void addNewLoans(int memberID, int bookID, Date loanDate, Date returnDate) {
		LoansDAO loansDAO = new LoansDAO();
		loansDAO.addNewLoans(memberID, bookID, loanDate, returnDate);
	}

	// 반납 추가
	public static void removeLoans(int loanID) {
		LoansDAO dao = new LoansDAO();
		dao.removeLoans(loanID);
		
	}
	
	
	
}
