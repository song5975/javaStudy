package javaProject8;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class LibraryMenu extends JFrame {

	private JPanel contentPane;
	private JButton btnForUsers, btnForAdmin, btnExit;
	
	private String name; // 현재 로그인한 사용자가 누구인지 확인하는 전역변수 name 

	// LibraryMain에서 로그인 시 LibraryMenu를 띄웠으므로 main문 필요 없음
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LibraryMenu frame = new LibraryMenu("");
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}


	public LibraryMenu(String name) {
		this.name = name;
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBounds(0, 0, 786, 81);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("메 뉴 선 택");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 28));
		lblNewLabel.setBounds(12, 10, 762, 61);
		pn1.add(lblNewLabel);
		
		JPanel pn2 = new JPanel();
		pn2.setBounds(0, 83, 786, 389);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		btnForUsers = new JButton("사 용 자 용");
		btnForUsers.setFont(new Font("굴림", Font.PLAIN, 18));
		btnForUsers.setBounds(457, 56, 183, 55);
		pn2.add(btnForUsers);
		
		btnForAdmin = new JButton("관 리 자 용");
		btnForAdmin.setFont(new Font("굴림", Font.PLAIN, 18));
		btnForAdmin.setBounds(457, 167, 183, 55);
		pn2.add(btnForAdmin);
		
		btnExit = new JButton("종 료");
		btnExit.setFont(new Font("굴림", Font.PLAIN, 18));
		btnExit.setBounds(457, 278, 183, 55);
		pn2.add(btnExit);
		
		JPanel pn3 = new JPanel();
		pn3.setBounds(0, 482, 786, 81);
		contentPane.add(pn3);
		pn3.setLayout(null);
		
		setVisible(true);
		
		/* ================================================ */
		
		// 사용자용 버튼
		btnForUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserScreen userScreen = new UserScreen(name);
				userScreen.setVisible(true);
			}
		});
		
		// 관리자용 버튼(개선점 : 2차 비밀번호 하드코딩, 환경 변수 혹은 외부 설정 파일을 사용하여 보완)
		btnForAdmin.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String secondPassword = JOptionPane.showInputDialog("2차 비밀번호를 입력하세요:");
		        // showInputDialog에서는 사용자가 입력 없이 취소를 누르면 null값 반환
		        // secondPassword != null를 통해서 NullPointerException 방지
		        if (secondPassword != null && secondPassword.equals("1234")) {
		            AdminScreen adminScreen = new AdminScreen();
		            adminScreen.setVisible(true);
		        } else {
		            JOptionPane.showMessageDialog(null, "2차 비밀번호가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		
		// 종료 버튼
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
	}
}
