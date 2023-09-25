package jPractice;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class LibraryMenu extends JFrame {

	private JPanel contentPane;
	private JButton btnForUsers, btnForAdmin, btnAccessorInfo;
	private String name;

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
        
//		System.out.println("LibraryMenu: name = " + name);
//		UserScreen userScreen = new UserScreen(name);
		
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
		
		btnAccessorInfo = new JButton("접속자 정보");
		btnAccessorInfo.setFont(new Font("굴림", Font.PLAIN, 18));
		btnAccessorInfo.setBounds(457, 278, 183, 55);
		pn2.add(btnAccessorInfo);
		
		JPanel pn3 = new JPanel();
		pn3.setBounds(0, 482, 786, 81);
		contentPane.add(pn3);
		pn3.setLayout(null);
		
		setVisible(true);
		
		/* ================================================ */
		
		// 사용자용 버튼
		btnForUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		        System.out.println("LibraryMenu: name = " + name);
				UserScreen userScreen = new UserScreen(name);
				userScreen.setVisible(true);
			}
		});
		
		// 관리자용 버튼
		btnForAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				AdminScreen adminScreen = new AdminScreen();
//				adminScreen.setVisible(true);
			}
		});
		
		// 접속자용 버튼
		btnAccessorInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				AccessorScreen accessorScreen = new AccessorScreen();
//				accessorScreen.setVisible(true);
			}
		});
		
	}
}
