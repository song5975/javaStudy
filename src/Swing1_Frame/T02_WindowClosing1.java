package Swing1_Frame;

import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

@SuppressWarnings("serial")
public class T02_WindowClosing1 extends Frame implements WindowListener{
	
	public T02_WindowClosing1() {
//		super("스윙연습!!");
		setTitle("스윙연습!!");
		setSize(300, 200);
		
		addWindowListener(this); // 메서드와 윈도우 창 간의 연결을 수립
	}
	
	public static void main(String[] args) {
		T02_WindowClosing1 t1 = new T02_WindowClosing1(); // 윈도우 창을 나타냄.
		t1.setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) { System.exit(0);}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}
}
