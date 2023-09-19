package t99_projectPractice;

import java.sql.Connection;
import java.util.Scanner;

public class BooksService {
	Scanner scanner = new Scanner(System.in);
	
	BooksDAO dao = new BooksDAO();
	BooksVO vo = null;
	
	public BooksService(Connection conn) {
    	this.conn = conn;
	}
	
	public void getBooksMenu() {
	        boolean run = true;
	        
	        while (run) {
	            System.out.print("\n1: 도서 등록 2: 도서 검색 3: 도서 목록 조회 4: 도서 삭제 5: 종료 ==> ");
	            int no = scanner.nextInt();
	            
	            switch (no) {
	                case 1:
	                    if (isAdmin() == true) {
	                        registerBook();
	                    } else {
	                        System.out.println("관리자 권한이 필요합니다.");
	                    }
	                    break;
	                case 2:
	                    searchBook();
	                    break;
	                case 3:
	                    listBooks();
	                    break;
	                case 4:
	                    if (isAdmin() == true) {
	                        deleteBook();
	                    } else {
	                        System.out.println("관리자 권한이 필요합니다.");
	                    }
	                    break;
	                default:
	                    run = false;
	            }
	        }
	        dao.connClose();
	    }
	    
	private boolean isAdmin() {
	    MembersDAO dao = new MembersDAO();    
	    System.out.println("관리자 비밀번호를 입력하세요. ");
	    String adminPassword = scanner.next();
	    
	    String actualAdminPassword = dao.getAdminPassword();
	    
	    if (adminPassword.equals(actualAdminPassword)) {
	        System.out.println("관리자로 확인되셨습니다.");
	        return true;
	    } else {
	        System.out.println("관리자 비밀번호가 일치하지 않습니다.");
	        return false;
	    }
	}


		private void registerBook() {
	        // 도서 등록 로직
		}
	    
	    private void searchBook() {
	        // 도서 검색 로직
	    }
	    
	    private void listBooks() {
	        // 도서 목록 조회 로직
	    }
	    
	    private void deleteBook() {
	        // 도서 삭제 로직
	    }	
}
