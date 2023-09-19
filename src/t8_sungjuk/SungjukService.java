package t8_sungjuk;

import java.util.ArrayList;
import java.util.Scanner;

public class SungjukService {
	Scanner scanner = new Scanner(System.in);
	SungjukDAO dao = new SungjukDAO();
	SungjukVO vo = null;
	
	String ans = "N";
	String name = "";
	
	// 성적 입력처리
	public void setInput() {
		vo = new SungjukVO();
		
		while(true) {
		System.out.println("\n**  성적 입력처리  **");
		System.out.print("성명 : "); vo.setName(scanner.next());
		System.out.print("국어 : "); vo.setKor(scanner.nextInt());
		System.out.print("영어 : "); vo.setEng(scanner.nextInt());
		System.out.print("수학 : "); vo.setMat(scanner.nextInt());
		
		// 동명 처리(같은 성명 자료는 입력하지 않는다.)
		
		int res = dao.setInput(vo);
		
		if(res == 0) System.out.println("성적 등록 실패");
		else System.out.println("성적이 등록되었습니다.");
		
		System.out.print("계속하시겠습니까?(y/n) => ");
		ans = scanner.next();
		if(ans.toUpperCase().equals("N")) break;
		}
//		scanner.close();
	}

	// 전체 리스트
	public void setList() {
		ArrayList<SungjukVO> vos = dao.getList();
		
		System.out.println("\n\t*** 성 적 리 스 트 ***");
		System.out.println("=".repeat(40));
		System.out.println("번호\t성명\t국어\t영어\t수학\t총점\t평균\t학점");
		System.out.println("-".repeat(40));
		for(int i=0; i<vos.size(); i++) {
			vo = vos.get(i);
			calculator(vo);
			System.out.print(vo.getIdx() + "\t");
			System.out.print(vo.getName() + "\t");
			System.out.print(vo.getKor() + "\t");
			System.out.print(vo.getEng() + "\t");
			System.out.print(vo.getMat() + "\t");
			System.out.print(vo.getTot() + "\t");
			System.out.print(String.format("%.1f", vo.getAvg()) + "\t");
			System.out.print(vo.getGrade() + "\n");
		}
		System.out.println("-".repeat(40));
		System.out.println("\t총 인원수 : "+vos.size()+" 명");
		System.out.println("=".repeat(40));
		
	}

	// 계산처리
	private void calculator(SungjukVO vo) {
		vo.setTot(vo.getKor()+vo.getEng()+vo.getMat());
		vo.setAvg(vo.getTot() / 3.0);
		if(vo.getAvg() >= 90) vo.setGrade('A');
		else if(vo.getAvg() >= 80) vo.setGrade('B');
		else if(vo.getAvg() >= 70) vo.setGrade('C');
		else if(vo.getAvg() >= 60) vo.setGrade('D');
		else vo.setGrade('F');
	}

	// 개별조회
	public void setSearch() {
		while(true) {
			System.out.print("\n조회할 성명을 입력하세요?");
			name = scanner.next();
			
			vo = dao.getSearch(name);
			
			if(vo != null) {
				calculator(vo);
				System.out.println("\n번호 : " + vo.getIdx());
				System.out.println("성명 : " + vo.getName());
				System.out.println("국어 : " + vo.getKor());
				System.out.println("영어 : " + vo.getEng());
				System.out.println("수학 : " + vo.getMat());
				System.out.println("총점 : " + vo.getTot());
				System.out.println("평균 : " + vo.getAvg());
				System.out.println("학점 : " + vo.getGrade());
			}
			else System.out.println("검색하신 "+name+"님은 없습니다.");
			
			System.out.print("계속 검색 하시겠습니까?(y/n) => ");
			ans = scanner.next();
			if(ans.toUpperCase().equals("N")) break;
		}
	}

	// 수정
	public void setUpdate() {
		Scanner scanner = new Scanner(System.in);
	    System.out.println("\n** 성적 수정 처리 **");
	    
	    // 수정할 학생의 이름을 입력.
	    System.out.print("수정할 학생의 성명을 입력하세요: ");
	    String name = scanner.next();
	    
	    // 입력받은 학생의 정보를 데이터베이스에서 조회.
	    SungjukVO vo = dao.getSearch(name);
	    
	    if (vo != null) {
	        // 학생 정보가 존재하면 수정할 정보를 입력.
	        System.out.print("새로운 국어 점수를 입력하세요: ");
	        int kor = scanner.nextInt();
	        
	        System.out.print("새로운 영어 점수를 입력하세요: ");
	        int eng = scanner.nextInt();
	        
	        System.out.print("새로운 수학 점수를 입력하세요: ");
	        int mat = scanner.nextInt();
	        
	        // 수정할 정보로 성적 정보 객체를 업데이트.
	        vo.setKor(kor);
	        vo.setEng(eng);
	        vo.setMat(mat);
	        
	        // 데이터베이스에 수정된 정보를 업데이트.
	        int res = dao.updateSungjuk(vo);
	        
	        if (res != 0) {
	            System.out.println("성적 정보가 수정되었습니다.");
	        } else {
	            System.out.println("성적 정보 수정 실패");
	        }
	    } else {
	        System.out.println("입력한 성명의 학생이 존재하지 않습니다.");
	    }
	    
	    scanner.close();
	}

	// 삭제
	public void setDelete() {
		Scanner scanner = new Scanner(System.in);
	    System.out.println("\n** 성적 삭제 처리 **");
	    
	    // 삭제할 학생의 이름을 입력.
	    System.out.print("삭제할 학생의 성명을 입력하세요: ");
	    String name = scanner.next();
	    
	    // 입력받은 성명을 사용하여 데이터베이스에서 해당 학생의 정보를 조회.
	    SungjukVO vo = dao.getSearch(name);
	    
	    if (vo != null) {
	        // 학생 정보가 존재하면 해당 학생의 정보를 삭제.
	        int res = dao.deleteSungjuk(vo.getIdx());
	        
	        if (res > 0) {
	            System.out.println("성적 정보가 삭제되었습니다.");
	        } else {
	            System.out.println("성적 정보 삭제 실패");
	        }
	    } else {
	        System.out.println("입력한 성명의 학생이 존재하지 않습니다.");
	    }
	    
	    scanner.close();
	}
	
}
