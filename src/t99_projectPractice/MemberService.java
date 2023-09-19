package t99_projectPractice;

import java.sql.Connection;
import java.util.Scanner;

public class MemberService {
    Scanner scanner = new Scanner(System.in);
    MembersDAO membersDAO = new MembersDAO();
    
    public MemberService(Connection conn) {
    	this.conn = conn;
    }

    public void getMemberMenu() {
        boolean run = true;

        while (run) {
            System.out.print("\n1: 회원 가입 2: 회원 정보 조회 3: 회원 정보 수정 4: 회원 정보 삭제 5: 종료 ==> ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    registerMember();
                    break;
                case 2:
                    viewMemberInfo();
                    break;
                case 3:
                    updateMemberInfo();
                    break;
                case 4:
                    deleteMember();
                    break;
                default:
                    run = false;
            }
        }
    }

    private void registerMember() {
        // 회원 가입 로직
    }

    private void viewMemberInfo() {
        // 회원 정보 조회 로직
    }

    private void updateMemberInfo() {
        // 회원 정보 수정 로직
    }

    private void deleteMember() {
        // 회원 정보 삭제 로직
    }

    // 관리자 비밀번호 확인 메서드
    private boolean isAdmin() {
        System.out.println("관리자 비밀번호를 입력하세요.");
        String adminPassword = scanner.next();

        // 실제 비밀번호를 데이터베이스에서 가져오는 로직
        String actualAdminPassword = membersDAO.getAdminPassword();

        if (adminPassword.equals(actualAdminPassword)) {
            System.out.println("관리자로 확인되셨습니다.");
            return true;
        } else {
            System.out.println("관리자 비밀번호가 일치하지 않습니다.");
            return false;
        }
    }
}
