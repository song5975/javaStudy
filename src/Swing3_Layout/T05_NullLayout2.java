package Swing3_Layout;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class T05_NullLayout2 extends JFrame {
	private JButton btnExit, btnInput, btnReset;
	private JLabel lblName, lblAge, lblTitle;
	private JTextField txtName, txtAge;
	private JTextArea txtaContent;
	
	private int xPos=50, yPos=100;

	public T05_NullLayout2() {
		setTitle("절대위치(Null) 레이아웃");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setLayout(null);
		
		add(getLblName());
		add(getLblAge());
		add(getTxtName());
		add(getTxtAge());
		
		add(getLblTitle());
		add(getTxtaContent());
		
		add(getBtnReset());
		add(getBtnInput());
		add(getBtnExit());
		
		/* ------------------------------------- */
		
		// 회원가입버튼
		btnInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = "";
				str += "성명 : " + txtName.getText() + "\n";
				str += "나이 : " + txtAge.getText();
				
				txtaContent.setText(str);
			}
		});
		
		// 리셋버튼
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtName.setText("");
				txtAge.setText("");
			}
		});
		
		// 종료버튼
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "회원가입 종료");
//				System.exit(0);
				dispose();
			}
		});
		
	}

	// 입력내용 확인하는 텍스트 박스
	private JTextArea getTxtaContent() {
		txtaContent = new JTextArea();
		txtaContent.setBackground(Color.CYAN);
		txtaContent.setBounds(280, 60, 150, 150);
		return txtaContent;
	}

	// 제목 레이블
	private JLabel getLblTitle() {
		lblTitle = new JLabel("\t회 원 가 입");
		lblTitle.setBackground(Color.RED);
		lblTitle.setBounds(280, 40, 150, 150);
		return lblTitle;
	}

	private JTextField getTxtAge() {
		txtAge = new JTextField();
		txtAge.setBounds(xPos+100, yPos+50, 100, 30);
		return txtAge;
	}

	private JTextField getTxtName() {
		txtName = new JTextField();
		txtName.setBounds(xPos+100, yPos, 100, 30);
		return txtName;
	}

	private JLabel getLblAge() {
		lblAge = new JLabel();
		lblAge.setText("나 이");
		lblAge.setBounds(xPos, yPos+50, 100, 30);
		return lblAge;
	}

	private JLabel getLblName() {
		lblName = new JLabel();
		lblName.setText("성 명");
		lblName.setBounds(xPos, yPos, 100, 30);
		return lblName;
	}
	
	/* 버튼 그룹 */

	private JButton getBtnInput() {
		btnInput = new JButton("회원가입");
		btnInput.setBounds(xPos, yPos+150, 100, 40);
		return btnInput;
	}
	
	private JButton getBtnReset() {
		btnReset = new JButton("다시입력");
		btnReset.setBounds(xPos+150, yPos+150, 100, 40);
		return btnReset;
	}
	

	private JButton getBtnExit() {
		btnExit = new JButton();
		btnExit.setText("종 료");
		btnExit.setBounds(xPos+300, yPos+150, 100, 40);
		return btnExit;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				T05_NullLayout2 t05 = new T05_NullLayout2();
				t05.setVisible(true);
			}
		});
	}
}
