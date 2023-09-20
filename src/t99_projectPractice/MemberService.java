package t99_projectPractice;

import java.util.Scanner;

public class MemberService {
    Scanner scanner = new Scanner(System.in);
    MembersDAO membersDAO = new MembersDAO();

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

    // LibraryRun에서 회원 아이디, 비밀번호를 받아서 확인하는 메소드.
    public static int checkLogin(String userID, String password) {
    	if (MembersDAO.isAdminID(userID) && MembersDAO.isAdminPassword(password)) {
    	    return 1; // 관리자 로그인
    	} else if (userID.equals(MembersDAO.ismemberID(userID)) && password.equals(MembersDAO.ismemberPassword(userID, password))) {
    	    return 2; // 사용자 로그인
    	} else {
    	    return 0; // 잘못된 비밀번호 또는 아이디
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

}
