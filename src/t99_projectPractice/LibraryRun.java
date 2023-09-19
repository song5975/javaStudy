package t99_projectPractice;

import java.sql.Connection;
import java.util.Scanner;

public class LibraryRun {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        // DatabaseConnector를 사용하여 데이터베이스 연결
        Connection conn = DatabaseConnector.getConnection();

        while (run) {
            boolean loggedIn = false;
            String adminID = "ADMIN"; // ADMIN 계정의 아이디
            String adminPassword = "1234"; // ADMIN 계정의 비밀번호

            System.out.print("아이디를 입력하세요: ");
            String userID = scanner.nextLine();
            System.out.print("비밀번호를 입력하세요: ");
            String password = scanner.nextLine();

            if (userID.equals(adminID) && password.equals(adminPassword)) {
                System.out.println("관리자로 로그인되었습니다.");
                loggedIn = true;
            } else {
                System.out.println("아이디 또는 비밀번호가 일치하지 않습니다. 다시 시도하세요.");
            }

            // 로그인이 성공하면 메뉴 선택
            if (loggedIn) {
                BooksService booksService = new BooksService(conn);
                MembersService membersService = new MembersService(conn);
                LoansService loansService = new LoansService(conn);

                int mainMenuChoice;

                do {
                    System.out.println("\n메인 메뉴");
                    System.out.println("1. 도서 관리");
                    System.out.println("2. 회원 관리");
                    System.out.println("3. 대출 관리");
                    System.out.println("4. 종료");
                    System.out.print("메뉴를 선택하세요: ");
                    mainMenuChoice = scanner.nextInt();

                    switch (mainMenuChoice) {
                        case 1:
                            booksService.getBooksMenu();
                            break;
                        case 2:
                            membersService.getMembersMenu();
                            break;
                        case 3:
                            loansService.getLoansMenu();
                            break;
                        case 4:
                            System.out.println("프로그램을 종료합니다.");
                            break;
                        default:
                            System.out.println("유효하지 않은 메뉴 선택입니다. 다시 선택하세요.");
                    }
                } while (mainMenuChoice != 4);

                // 사용 후 데이터베이스 연결 종료
                DatabaseConnector.closeConnection(conn);
                run = false; // 프로그램 종료
            }
        }
    }
}
