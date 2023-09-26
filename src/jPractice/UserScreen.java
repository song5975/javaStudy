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
    
    private String name; // 현재 로그인한 사용자가 누구인지 확인하는 전역변수 name 
    

//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//					UserScreen frame = new UserScreen();
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    public UserScreen(String name) {
        System.out.println("UserScreen_Debug: name = " + name);
    	this.name = name;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // 검색 필드 추가(도서 테이블)
        keywordField = new JTextField();
        keywordField.setBounds(12, 10, 150, 30);
        contentPane.add(keywordField);

        // 검색 컬럼 ComboBox(도서 테이블)
        searchColumnComboBox = new JComboBox<String>();
        searchColumnComboBox.setBounds(174, 10, 150, 30);
        searchColumnComboBox.addItem("bookID");
        searchColumnComboBox.addItem("title");
        searchColumnComboBox.addItem("author");
        searchColumnComboBox.addItem("publisher");
        searchColumnComboBox.addItem("genre");
        searchColumnComboBox.addItem("isAvailable");
        contentPane.add(searchColumnComboBox);

        // (도서 테이블)
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

        // 검색 버튼(도서 테이블)
        searchButton = new JButton("검색");
        searchButton.setBounds(336, 10, 80, 30);
        contentPane.add(searchButton);

        // 전체 검색 버튼(도서 테이블)
        showAllButton = new JButton("전체 검색");
        showAllButton.setBounds(428, 10, 100, 30);
        contentPane.add(showAllButton);

        // 대출 버튼(도서 테이블)
        btnLoans = new JButton("도 서 대 출");
        btnLoans.setFont(new Font("굴림", Font.PLAIN, 18));
        btnLoans.setBounds(618, 224, 138, 38);
        contentPane.add(btnLoans);

        // (대출 테이블)
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

        // 접속자 대출 조회(대출 테이블)
        showMyLoans = new JButton("대출 기록 조회");
        showMyLoans.setBounds(12, 279, 161, 30);
        contentPane.add(showMyLoans);

        // 반납 버튼(대출 테이블)
        btnReturn = new JButton("도 서 반 납");
        btnReturn.setFont(new Font("굴림", Font.PLAIN, 18));
        btnReturn.setBounds(618, 444, 138, 38);
        contentPane.add(btnReturn);

        // 이전 버튼
        btnBack = new JButton("이 전 으 로");
        btnBack.setFont(new Font("굴림", Font.PLAIN, 18));
        btnBack.setBounds(618, 501, 138, 38);
        contentPane.add(btnBack);

        /* ================================================ */

        // 검색 버튼(도서 테이블)
        searchButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		BooksService booksService = new BooksService();
        		String selectedColumn = (String) searchColumnComboBox.getSelectedItem();
        		String keyword = keywordField.getText();
        		
        		System.out.println("searchButton_Debug : " + selectedColumn);
        		System.out.println("searchButton_Debug : " + keyword);
        		
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
       
        // 전체 검색 버튼(도서 테이블)
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

        // 대출 버튼(도서 테이블)
        btnLoans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int bookID = (int) table.getValueAt(selectedRow, 0);
                    boolean isAvailable = (boolean) table.getValueAt(selectedRow, 5);
                    
                    if (isAvailable) {
                        BooksService booksService = new BooksService();
                        
                        System.out.println("대출버튼_bookID_Debug:" + bookID);
                        System.out.println("대출버튼_isAvailable_Debug:" + isAvailable);
                        
                        
                        // 대출 기록을 추가할 때 해당 도서의 isAvailable 값을 수정
                        booksService.availableControl(bookID, isAvailable);
                        // 대출일을 현재 날짜로 설정
                        Date loanDate = new Date(System.currentTimeMillis());
                        // 반납 예정일을 대출일로부터 14일 후로 설정
                        Date returnDate = new Date(System.currentTimeMillis() + (14 * 24 * 60 * 60 * 1000));
                       
                        LoansService loansService = new LoansService();
                        MemberService memberService = new MemberService();
                        
                        System.out.println("UserScreenButton_Debug: name = " + name);
                        int memberID = memberService.getCurrentUserID(name);
                        loansService.addNewLoans(memberID, bookID, loanDate, returnDate);
                        JOptionPane.showMessageDialog(null, "대출이 정상적으로 처리되었습니다.");
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "선택한 도서는 대출이 불가능합니다.", "알림", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "도서를 선택하세요.", "알림", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // 현재 접속자 대출 조회(대출 테이블)
        showMyLoans.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoansService loansService = new LoansService();
		        
				System.out.println("showMyLoans_Debug: name = " + name);
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
				
        // 반납 버튼(대출 테이블)
        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = loanTable.getSelectedRow();
                if (selectedRow != -1) {
                    int loanID = (int) loanTable.getValueAt(selectedRow, 0);

                    LoansService loansService = new LoansService();
                	LoansVO loansVO = loansService.getLoanByID(loanID);
                    // 대출 기록을 삭제할 때 해당 도서의 isAvailable 값을 수정
                    if (loansVO != null) {
                        int bookID = loansVO.getBookID();
                        boolean isAvailable = false;
                        BooksService booksService = new BooksService();
                        booksService.availableControl(bookID, isAvailable);
                        
                        loansService.removeLoans(loanID); // 대출 기록 삭제
                    }
                    JOptionPane.showMessageDialog(null, "반납이 정상적으로 처리되었습니다.");
                } else {
                    JOptionPane.showMessageDialog(null, "대출 기록을 선택하세요.", "알림", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        // 이전 버튼
        btnBack.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        
        setVisible(true);
    }
}
