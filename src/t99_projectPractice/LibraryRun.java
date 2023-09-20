package t99_projectPractice;

import java.util.Scanner;

public class LibraryRun {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
    	boolean run = true;
    	boolean loggedIn = false; // 로그인 상태를 외부에서 선언

    	while (run) {
    	    System.out.print("아이디를 입력하세요: ");
    	    String userID = scanner.nextLine();
    	    System.out.print("비밀번호를 입력하세요: ");
    	    String password = scanner.nextLine();
    	    
    	    int loginResult = MemberService.checkLogin(userID, password);

    	    switch (loginResult) {
    	        case 1:
    	            System.out.println("관리자로 로그인되었습니다.");
    	            loggedIn = true; // 로그인 성공 시 로그인 상태를 변경
    	            break;
    	        case 2:
    	            System.out.println("사용자로 로그인되었습니다.");
    	            loggedIn = true; // 로그인 성공 시 로그인 상태를 변경
    	            break;
    	        default:
    	            System.out.println("아이디 또는 비밀번호가 일치하지 않습니다. 다시 시도하세요.");
    	            break;
    	    }

    	    while (loggedIn) { // 로그인 상태일 때에만 메뉴 선택 가능
    	        System.out.println("\n\t*** 작업선택(메뉴선택) ***");
    	        System.out.println("=========================================");
    	        System.out.print("1:도서관리   2:회원관리   3:대출관리   4:종료 ==> ");
    	        int no = scanner.nextInt();

    	        switch (no) {
    	            case 1:
    	                BooksService bService = new BooksService();
    	                bService.getBooksMenu();
    	                break;
    	            case 2:
    	                // MemberService mService = new MemberService();
    	                // mService.getMembersMenu();
    	                break;
    	            case 3:
    	                // LoansService lService = new LoansService();
    	                // lService.getLoansMenu();
    	                break;
    	            case 4:
    	                System.out.println("프로그램을 종료합니다.");
    	                loggedIn = false; // 로그아웃 시 로그인 상태 변경
    	                break;
    	            default:
    	                System.out.println("유효하지 않은 메뉴 선택입니다. 다시 선택하세요.");
    	        }
    	    }
    	}
    }
}