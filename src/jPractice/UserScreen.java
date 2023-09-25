package jPractice;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class UserScreen extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField keywordField;
    private JComboBox<String> searchColumnComboBox;
    private JButton searchButton, showAllButton, btnLoans, btnReturn, btnBack, showMyLoans;
    
	private String name;
    

//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                	System.out.println("A1");
//					UserScreen frame = new UserScreen();
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    public UserScreen(String name) {
        System.out.println("UserScreen: name = " + name);
    	this.name = name;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // 검색 필드 추가
        keywordField = new JTextField();
        keywordField.setBounds(12, 10, 150, 30);
        contentPane.add(keywordField);

        // 검색 컬럼 선택 ComboBox (위의 테이블용)
        searchColumnComboBox = new JComboBox<String>();
        searchColumnComboBox.setBounds(174, 10, 150, 30);
        searchColumnComboBox.addItem("도서 ID");
        searchColumnComboBox.addItem("도서 제목");
        searchColumnComboBox.addItem("작가");
        searchColumnComboBox.addItem("출판사");
        searchColumnComboBox.addItem("장르");
        searchColumnComboBox.addItem("대출 가능 여부");
        contentPane.add(searchColumnComboBox);

        // JTable (위의 테이블용)
        tableModel = new DefaultTableModel();
        tableModel.addColumn("도서 ID");
        tableModel.addColumn("도서 제목");
        tableModel.addColumn("작가");
        tableModel.addColumn("출판사");
        tableModel.addColumn("장르");
        tableModel.addColumn("대출 가능 여부");

        table = new JTable(tableModel);
        JScrollPane scrollPane1 = new JScrollPane(table);
        scrollPane1.setBounds(12, 50, 762, 164);
        contentPane.add(scrollPane1);

        // 검색 버튼 (위의 테이블용)
        searchButton = new JButton("검색");
        searchButton.setBounds(336, 10, 80, 30);
        contentPane.add(searchButton);

        // 전체 검색 버튼 (위의 테이블용)
        showAllButton = new JButton("전체 검색");
        showAllButton.setBounds(428, 10, 100, 30);
        contentPane.add(showAllButton);

        // 대출 버튼 (위의 테이블용)
        btnLoans = new JButton("도 서 대 출");
        btnLoans.setFont(new Font("굴림", Font.PLAIN, 18));
        btnLoans.setBounds(618, 224, 138, 38);
        contentPane.add(btnLoans);

        // JTable (아래의 테이블용 - 대출 기록 조회)
        DefaultTableModel loanTableModel = new DefaultTableModel();
        loanTableModel.addColumn("대출 ID");
        loanTableModel.addColumn("회원 ID");
        loanTableModel.addColumn("도서 ID");
        loanTableModel.addColumn("대출일");
        loanTableModel.addColumn("반납일");

        JTable loanTable = new JTable(loanTableModel);
        JScrollPane scrollPane2 = new JScrollPane(loanTable);
        scrollPane2.setBounds(12, 320, 762, 108);
        contentPane.add(scrollPane2);

        // 접속자 대출 조회(아래의 테이블용)
        showMyLoans = new JButton("대출 기록 조회");
        showMyLoans.setBounds(12, 279, 161, 30);
        contentPane.add(showMyLoans);

        // 반납 버튼 (아래의 테이블용)
        btnReturn = new JButton("도 서 반 납");
        btnReturn.setFont(new Font("굴림", Font.PLAIN, 18));
        btnReturn.setBounds(618, 444, 138, 38);
        contentPane.add(btnReturn);

        // 이전 버튼 (아래의 테이블용)
        btnBack = new JButton("이 전 으 로");
        btnBack.setFont(new Font("굴림", Font.PLAIN, 18));
        btnBack.setBounds(618, 501, 138, 38);
        contentPane.add(btnBack);

        
        /* ================================================ */

        // 검색 버튼 (위의 테이블용)
        searchButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		BooksService booksService = new BooksService();
        		String selectedColumn = (String) searchColumnComboBox.getSelectedItem();
        		String keyword = keywordField.getText();
        		
        		// 검색 대상 열과 키워드를 BooksService로 전달하여 검색 수행
        		List<BooksVO> vos = booksService.searchBooks(selectedColumn, keyword);
        		
        		// 테이블 모델 초기화
        		DefaultTableModel model = (DefaultTableModel) table.getModel();
        		model.setRowCount(0); // 기존 데이터 모두 제거
        		
        		// 검색 결과를 테이블에 추가
        		for (BooksVO vo : vos) {
        			model.addRow(new Object[] {
        					vo.getBookID(),
        					vo.getTitle(),
        					vo.getAuthor(),
        					vo.getPublisher(),
        					vo.getGenre(),
        					vo.isAvailable()
        			});
        		}
        	}
        });
       
        // 전체 검색 버튼 (위의 테이블용)
        showAllButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		BooksService booksService = new BooksService();
        		List<BooksVO> vos = booksService.showAllBooks();
        		 
        		// 테이블 모델 초기화
        	    DefaultTableModel model = (DefaultTableModel) table.getModel();
        	    model.setRowCount(0); // 기존 데이터 모두 제거
        		 
        		// vos의 데이터를 테이블에 추가
        	        for (BooksVO vo : vos) {
        	            model.addRow(new Object[] {
        	                vo.getBookID(),
        	                vo.getTitle(),
        	                vo.getAuthor(),
        	                vo.getPublisher(),
        	                vo.getGenre(),
        	                vo.isAvailable()
        	            });
        	        }
        	}
        });


     // 대출 버튼 (위의 테이블용)
        btnLoans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int bookID = (int) table.getValueAt(selectedRow, 0);
                    boolean isAvailable = (boolean) table.getValueAt(selectedRow, 5);
                    
//                    if (isAvailable) {
                        BooksService booksService = new BooksService();
                        booksService.availableControl(bookID, isAvailable);
                        // 대출일을 현재 날짜로 설정
                        Date loanDate = new Date(System.currentTimeMillis());
                        // 반납 예정일을 대출일로부터 14일 후로 설정
                        Date returnDate = new Date(System.currentTimeMillis() + (14 * 24 * 60 * 60 * 1000));
                       
                        LoansService loansService = new LoansService();
                        MemberService memberService = new MemberService();
                        
                        System.out.println("UserScreenButton: name = " + name);
                        int memberID = memberService.getCurrentUserID(name);
                        loansService.addNewLoans(memberID, bookID, loanDate, returnDate);
                        JOptionPane.showMessageDialog(null, "대출이 정상적으로 처리되었습니다.");
                        
//                    } else {
//                        JOptionPane.showMessageDialog(null, "선택한 도서는 대출이 불가능합니다.", "알림", JOptionPane.WARNING_MESSAGE);
//                    }
                } else {
                    JOptionPane.showMessageDialog(null, "도서를 선택하세요.", "알림", JOptionPane.WARNING_MESSAGE);
                }
                
            }
        });

        // 접속자 대출 조회(아래의 테이블용)
        showMyLoans.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoansService loansService = new LoansService();
		        
				System.out.println("접속자 대출 조회: name = " + name);
                MemberService memberService = new MemberService();
		        int memberID = memberService.getCurrentUserID(name);

        		List<LoansVO> vos = loansService.showAllLoans(memberID);
        		
        		// 테이블 모델 초기화
        	    DefaultTableModel model = (DefaultTableModel) loanTable.getModel();
        	    model.setRowCount(0); // 기존 데이터 모두 제거
        		 
        		// vos의 데이터를 테이블에 추가
        	        for (LoansVO vo : vos) {
        	            model.addRow(new Object[] {
        	                vo.getLoanID(),
        	                vo.getMemberID(),
        	                vo.getBookID(),
        	                vo.getLoanDate(),
        	                vo.getReturnDate(),
        	            });
        	        }
        	}
        });
				
        // 반납 버튼(아래의 테이블용)
        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = loanTable.getSelectedRow();
                if (selectedRow != -1) {
                    int loanID = (int) loanTable.getValueAt(selectedRow, 0);

                    LoansService loansService = new LoansService();
                    LoansService.removeLoans(loanID);
                    
                    
             	}
            }
        });
   
                   
        
        // 이전 버튼
        btnLoans.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        
        setVisible(true);
    }
}
