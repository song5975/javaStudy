package Swing3_Layout;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/*
  - CardLayout 사용법
  - CardLayout 제어 메소드 : first(), last(), next(), show()
  - first() : 첫번째 카드 보이기
  - last()  : 마지막 카드 보이기
  - next()  : 다음 카드 보이기
  - show()  : 지정된 카드 보이기
 */
@SuppressWarnings("serial")
public class T04_CardLayout2 extends JFrame {
	private JPanel pnb1, pnb2, pnb3, pn1, pn2, pn3;
	private JButton btn1, btn2;
	private JLabel lbl1;
	
	public T04_CardLayout2() {
		setTitle("CardLayout연습");
		setSize(250, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setLayout(new BorderLayout());
		//BorderLayout bLayer = new BorderLayout();
		
		pnb1 = new JPanel();
		pnb1.setBackground(Color.CYAN);
		pnb2 = new JPanel();
		pnb2.setBackground(Color.magenta);
		
		CardLayout card = new CardLayout();
		//setLayout(new CardLayout());
		
		lbl1 = new JLabel();
		lbl1.setText("이곳은 레이블 입니다.");
		
		pnb3 = new JPanel();
		pnb3.add(lbl1);
		
		
		pnb1.setLayout(card);
		
		btn1 = new JButton("버튼1");
		btn2 = new JButton("버튼2");
		
		pnb2.add(btn1);
		pnb2.add(btn2);
		
		add(pnb1, BorderLayout.CENTER);
		add(pnb2, BorderLayout.SOUTH);		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				T04_CardLayout2 t04 = new T04_CardLayout2();
				t04.setVisible(true);
			}
		});
	}
}