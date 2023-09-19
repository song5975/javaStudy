package t4_hoiwon;

import java.util.ArrayList;
import java.util.Scanner;

public class HoiwonRun {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		HoiwonDAO dao = new HoiwonDAO();
		boolean run = true;
		String name = "";
		HoiwonVO vo = null;
		
		while(run) {
			System.out.print("1.전체조회 2.개별조회 3.수정(내일) 4.삭제 5.종료 ==> ");
			int no = scanner.nextInt();
			
			switch (no) {
				case 1:
					ArrayList<HoiwonVO> vos = dao.getList(); // 전체검색 호출
					
					System.out.println("\t\t** 주 소 록 **");
					System.out.println("=".repeat(50));
					System.out.println("번호\t성명\t나이\t주소\t성별");
					System.out.println("-".repeat(50));
					for(HoiwonVO v : vos) {
						System.out.print(v.getIdx() + "\t");
						System.out.print(v.getName() +"\t");
						System.out.print(v.getAge() + "\t");
						System.out.print(v.getAddress() + "\t");
						System.out.print(v.getGender());
						System.out.println();
					}
					System.out.println("=".repeat(50));
					
					break;
				case 2:
					System.out.print("검색할 회원명을 입력하세요?(종료 시 999) => ");
					name = scanner.next();
					System.out.println("-".repeat(50));
					
					vo = dao.getSearch(name); //개별검색 호출
//					if(vo != null) { // vo값이 null이 되지 않기에 오류.
					if(vo.getName() != null) { // 이걸 그냥 vo로 하면 오류.
//						System.out.println(vo.toString());
						System.out.println("번호 : " + vo.getIdx());
						System.out.println("성명 : " + vo.getName());
						System.out.println("나이 : " + vo.getAge());
						System.out.println("주소 : " +vo.getAddress());
						System.out.println("성별 : " +vo.getGender());
					}
					else {
						System.out.println(name + "님 자료가 없습니다.");
					}
					break;
				case 3: // 수정
					
					break;
				case 4: // 삭제처리
					System.out.print("삭제할 회원명을 입력하세요?(종료 시 999) => ");
					name = scanner.next();
					System.out.println("-".repeat(50));
					
					vo = dao.getSearch(name);
					if(vo.getName() == null) System.out.println(name + "님 자료가 없습니다.");
					else {
						dao.setDelete(name);
						System.out.println(name + "님 자료가 삭제되었습니다.");
					}
					break;
				default:
					run = false;
			}
			System.out.println("=".repeat(50));
		}
		System.out.println("끝");
		
		// DB conn 객체 close
		dao.connClose();
		
		scanner.close();
	}
}
