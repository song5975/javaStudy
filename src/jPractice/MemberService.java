package jPractice;

import java.util.List;

public class MemberService {
	
	MemberDAO dao = new MemberDAO();
	
	// 로그인 시 회원의 아이디와 비밀번호가 맞는지 확인하는 메소드
	public boolean memberCheck(String username, String password) {
	        // MemberDAO 클래스를 사용하여  DB에서 아이디와 비밀번호 확인
	        return MemberDAO.memberCheck(username, password);
	    }

	// 회원가입 시 유효성 검사
    public boolean validationCheck(String name, String password, String contact, String address) {
        if (name.trim().equals("") || password.trim().equals("")) {
            return false;
        }
        if (contact.trim().equals("") || address.trim().equals("")) {
            return false;
        }
        if (!contact.matches("\\d{3}-\\d{4}-\\d{4}")) {
            return false;
        }
        if (dao.memberCheck(name, password)) {
            return false;
        }
        return true;
    }
   
    // 회원정보 수정 시 유효성 검사
    public boolean newValidationCheck(String name, String password, String contact, String address) {
    	if (name.trim().equals("") || password.trim().equals("")) {
    		return false;
    	}
    	if (contact.trim().equals("") || address.trim().equals("")) {
    		return false;
    	}
    	if (!contact.matches("\\d{3}-\\d{4}-\\d{4}")) {
    		return false;
    	}
    	return true;
    }

    // 유효성 검사가 끝난 신규회원을 추가
	public boolean addMember(String name, String password, String contact, String address) {
	    boolean isAdded = dao.addMember(name, password, contact, address);
	    return isAdded;
	}

	// 현재 사용자의 이름을 받아서 ID를 가져오는 메소드
	public int getCurrentUserID(String name) {
        System.out.println("MemberService_Debug: name = " + name);
		int memberID = dao.getCurrentUserID(name);
		
		return memberID;
	}

	// 전체 회원 조회 버튼(회원 테이블)
	public List<MembersVO> showAllMembers() {
	    List<MembersVO> vos = dao.getAllMembers();
	    return vos;
	}

    // 회원 정보 수정 버튼(회원 테이블)
	public boolean updateMember(int memberID, String newName, String newPassword, String newContact, String newAddress) {
		
    	System.out.println("update_debug: " + newName + newPassword + newContact + newAddress);
		
		boolean isValid = dao.updateMember(memberID, newName, newPassword, newContact, newAddress);
		return isValid;
	}

    // 회원 정보 삭제(회원 테이블)
	public boolean deleteMember(int memberID) {
//		MemberDAO memberDAO = new MemberDAO();
		
		System.out.println("Debug1 : " + memberID);
		
        boolean isSuccess = dao.deleteMember(memberID);
		return isSuccess;
	}
 
}