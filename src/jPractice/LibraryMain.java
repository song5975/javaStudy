package jPractice;

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
public class LibraryMain extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldID;
    private JTextField textFieldPassword;
    private JButton btnLogIn, btnCreateNew;
    
    private String name;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LibraryMain frame = new LibraryMain();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    
    public LibraryMain() {
        setTitle("도 서 관");
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
        
        JLabel lblNewLabel = new JLabel("도 서 관");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 28));
        lblNewLabel.setBounds(12, 10, 762, 61);
        pn1.add(lblNewLabel);
        
        JPanel pn2 = new JPanel();
        pn2.setBounds(0, 83, 786, 389);
        contentPane.add(pn2);
        pn2.setLayout(null);
        
        JLabel lblID = new JLabel("회원 ID");
        lblID.setFont(new Font("굴림", Font.PLAIN, 18));
        lblID.setHorizontalAlignment(SwingConstants.CENTER);
        lblID.setBounds(379, 120, 140, 38);
        pn2.add(lblID);
        
        JLabel lblPassword = new JLabel("회원 Password");
        lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lblPassword.setFont(new Font("굴림", Font.PLAIN, 18));
        lblPassword.setBounds(379, 183, 140, 38);
        pn2.add(lblPassword);
        
        textFieldID = new JTextField();
        textFieldID.setFont(new Font("굴림", Font.PLAIN, 18));
        textFieldID.setBounds(558, 120, 183, 38);
        pn2.add(textFieldID);
        textFieldID.setColumns(10);
        
        textFieldPassword = new JTextField();
        textFieldPassword.setFont(new Font("굴림", Font.PLAIN, 18));
        textFieldPassword.setColumns(10);
        textFieldPassword.setBounds(558, 183, 183, 38);
        pn2.add(textFieldPassword);
        
        btnLogIn = new JButton("로 그 인");
        btnLogIn.setBounds(379, 282, 140, 38);
        pn2.add(btnLogIn);
        
        btnCreateNew = new JButton("회 원 가 입");
        btnCreateNew.setBounds(583, 282, 140, 38);
        pn2.add(btnCreateNew);
        
        JPanel pn3 = new JPanel();
        pn3.setBounds(0, 482, 786, 81);
        contentPane.add(pn3);
        pn3.setLayout(null);
        
        /* ================================================ */

        // 로그인 버튼
        btnLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	name = textFieldID.getText();
                String password = textFieldPassword.getText();
                
                // MemberService 클래스에서 아이디, 비밀번호 검사
                MemberService service = new MemberService();
                boolean isValid = service.memberCheck(name, password);
                
                if (isValid) {
                    // 모든 체크가 끝나면 로그인 완료.
                    
                    JOptionPane.showMessageDialog(null, name + "님 환영합니다.");
                    System.out.println("LibraryMain: name = " + name);

                    LibraryMenu libraryMenu = new LibraryMenu(name);
                    libraryMenu.setVisible(true);
                    
                } else {
                    // DB정보와 일치하지 않는 경우 메시지 표시
                    JOptionPane.showMessageDialog(null, "이름 또는 비밀번호를 확인하세요.");
                }
            }

        });
       
        // 회원가입 버튼
        btnCreateNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MemberInput memberInput = new MemberInput(); // 회원가입 view를 만들어서 호출
                memberInput.setVisible(true);
            }
        });
        
        textFieldID.setText("강감찬");
        textFieldPassword.setText("kang2222");
    }
}
