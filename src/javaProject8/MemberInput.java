package javaProject8;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MemberInput extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNewID;
	private JTextField textFieldNewPassword;
	private JTextField textFieldNewPhoneNum;
	private JTextField textFieldNewAddress;
	private JButton btnNewMemberInput, btnExit;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberInput frame = new MemberInput();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MemberInput() {
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
		
		JLabel lblNewLabel = new JLabel("회 원 가 입");
		lblNewLabel.setBounds(12, 10, 762, 61);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 28));
		pn1.add(lblNewLabel);
		
		JPanel pn2 = new JPanel();
		pn2.setBounds(0, 83, 786, 389);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		JLabel lblNewID = new JLabel("ID");
		lblNewID.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewID.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewID.setBounds(79, 26, 140, 38);
		pn2.add(lblNewID);
		
		JLabel lblNewPassword = new JLabel("Password");
		lblNewPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewPassword.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewPassword.setBounds(79, 100, 140, 38);
		pn2.add(lblNewPassword);
		
		JLabel lblNewPhoneNum = new JLabel("PhoneNum");
		lblNewPhoneNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewPhoneNum.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewPhoneNum.setBounds(79, 174, 140, 38);
		pn2.add(lblNewPhoneNum);
		
		JLabel lblNewAddress = new JLabel("Address");
		lblNewAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewAddress.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewAddress.setBounds(79, 248, 140, 38);
		pn2.add(lblNewAddress);
		
		textFieldNewID = new JTextField();
		textFieldNewID.setFont(new Font("굴림", Font.PLAIN, 18));
		textFieldNewID.setBounds(300, 26, 183, 38);
		pn2.add(textFieldNewID);
		textFieldNewID.setColumns(10);
		
		textFieldNewPassword = new JTextField();
		textFieldNewPassword.setFont(new Font("굴림", Font.PLAIN, 18));
		textFieldNewPassword.setColumns(10);
		textFieldNewPassword.setBounds(300, 100, 183, 38);
		pn2.add(textFieldNewPassword);
		
		textFieldNewPhoneNum = new JTextField();
		textFieldNewPhoneNum.setFont(new Font("굴림", Font.PLAIN, 18));
		textFieldNewPhoneNum.setColumns(10);
		textFieldNewPhoneNum.setBounds(300, 174, 183, 38);
		pn2.add(textFieldNewPhoneNum);
		
		textFieldNewAddress = new JTextField();
		textFieldNewAddress.setFont(new Font("굴림", Font.PLAIN, 18));
		textFieldNewAddress.setColumns(10);
		textFieldNewAddress.setBounds(300, 248, 183, 38);
		pn2.add(textFieldNewAddress);
		
		btnNewMemberInput = new JButton("회 원 가 입");
		btnNewMemberInput.setFont(new Font("굴림", Font.PLAIN, 18));
		btnNewMemberInput.setBounds(565, 157, 169, 55);
		pn2.add(btnNewMemberInput);
		
		btnExit = new JButton("취 소");
		btnExit.setFont(new Font("굴림", Font.PLAIN, 18));
		btnExit.setBounds(565, 241, 169, 55);
		pn2.add(btnExit);
		
		JPanel pn3 = new JPanel();
		pn3.setBounds(0, 475, 786, 78);
		contentPane.add(pn3);
		
		/* ================================================ */
		
		// 회원가입 버튼
		btnNewMemberInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = textFieldNewID.getText();
				String password = textFieldNewPassword.getText();
				String contact = textFieldNewPhoneNum.getText();
				String address = textFieldNewAddress.getText();
				
				// MemberService에서 신규회원의 정보 유효성 검사
				MemberService service = new MemberService();
				boolean isValid = service.validationCheck(name, password, contact, address);
				
		        if (isValid) {
		            boolean isAdded = service.addMember(name, password, contact, address);
		            if (isAdded) {
		                JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.", "회원가입 성공", JOptionPane.INFORMATION_MESSAGE);
		                dispose();
		            } else {
		                JOptionPane.showMessageDialog(null, "회원가입에 실패하였습니다. 중복된 아이디일 수 있습니다.", "회원가입 실패", JOptionPane.ERROR_MESSAGE);
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "입력 정보가 유효하지 않습니다.", "유효성 검사 실패", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});

		// 가입취소 버튼
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
	}
}
