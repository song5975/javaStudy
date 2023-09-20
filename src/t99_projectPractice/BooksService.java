package t99_projectPractice;

import java.util.Scanner;

public class BooksService {
	Scanner scanner = new Scanner(System.in);
	
	BooksDAO dao = new BooksDAO();
	BooksVO vo = null;
	
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
	    }
	    
	private boolean isAdmin() {
	    
	    System.out.println("2차 비밀번호를 입력하세요: ");
	    String secondPassword = scanner.next();
	    
	    if ("1234".equals(secondPassword)) {
	        System.out.println("관리자로 확인되셨습니다.");
	        return true;
	    } else {
	        System.out.println("관리자 비밀번호가 일치하지 않습니다.");
	        return false;
	    }
	}


		private void registerBook() { // 여기서 데이터베이스에 접근하려면 dao 호출
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
