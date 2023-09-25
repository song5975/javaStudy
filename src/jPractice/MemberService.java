package jPractice;

public class MemberService {
	
	MemberDAO dao = new MemberDAO();
	
	// 로그인 시 회원의 아이디와 비밀번호가 맞는지 확인하는 메소드
	public boolean memberCheck(String username, String password) {
	        // MemberDAO 클래스를 사용하여  DB에서 아이디와 비밀번호 확인
	        return MemberDAO.memberCheck(username, password);
	    }

	
    public boolean validationCheck(String name, String password, String contact, String address) {
    	// 이름, 비밀번호 유효성 검사
        if (name.trim().equals("") || password.trim().equals("")) {
            return false; // 아이디 또는 비밀번호가 비어있는 경우
        }
        
        // 전화번호와 주소의 유효성 검사
        if (contact.trim().equals("") || address.trim().equals("")) {
            return false; // 전화번호 또는 주소가 비어있는 경우
        }
        
        // 전화번호 유효성 검사 ((숫자 3개-숫자 4개-숫자 4개)
        if (!contact.matches("\\d{3}-\\d{4}-\\d{4}")) {
            return false; // 전화번호에 숫자나 -이 아닌 문자가 포함된 경우
        }
        
        // 중복 확인
        if (dao.memberCheck(name, password)) {
            return false; // 이미 있는 회원일 경우
        }
        
        return true; // 모든 필드가 유효하고 중복되지 않은 경우
    }


	public boolean addMember(String name, String password, String contact, String address) {
		  // DAO를 통해 데이터베이스에 회원 정보 추가
	    boolean isAdded = dao.addMember(name, password, contact, address);
	    
	    return isAdded;
	}

	// 현재 사용자의 이름을 받아서 ID를 가져오는 메소드
	public int getCurrentUserID(String name) {
        System.out.println("MemberService: name = " + name);

		int memberID = dao.getCurrentUserID(name);
		
		return memberID;
	}
    
 
}