package jPractice;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class AdminScreen extends JFrame {

	private JPanel contentPane;
	private JTable bookTable, loanTable, memberTable;
    private JTextField keywordField;
    private JComboBox<String> searchColumnComboBox;
    private JButton searchButton, showAllButton, btnAddBooks, btnDeleteBooks; // bookTable 버튼
    private JButton showAllLoans;											  // loanTable 버튼
    private JButton showAllMembers, btnMemberAlter, btnMemberDelete, btnBack; // memberTable 버튼


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminScreen frame = new AdminScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminScreen() {
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
        DefaultTableModel bookTableModel = new DefaultTableModel();
        bookTableModel.addColumn("도서 ID");
        bookTableModel.addColumn("도서 제목");
        bookTableModel.addColumn("작가");
        bookTableModel.addColumn("출판사");
        bookTableModel.addColumn("장르");
        bookTableModel.addColumn("대출 가능 여부");

        bookTable = new JTable(bookTableModel);
        JScrollPane scrollPaneBook = new JScrollPane(bookTable);
        scrollPaneBook.setBounds(12, 50, 762, 70);
        contentPane.add(scrollPaneBook);

        // 검색 버튼(도서 테이블)
        searchButton = new JButton("검색");
        searchButton.setBounds(336, 10, 80, 30);
        contentPane.add(searchButton);

        // 전체 검색 버튼(도서 테이블)
        showAllButton = new JButton("전체 검색");
        showAllButton.setBounds(428, 10, 100, 30);
        contentPane.add(showAllButton);
        
        // 도서 추가 버튼(도서 테이블)
        btnAddBooks = new JButton("도 서 등 록");
        btnAddBooks.setFont(new Font("굴림", Font.PLAIN, 18));
        btnAddBooks.setBounds(468, 130, 138, 38);
        contentPane.add(btnAddBooks);

        // 도서 삭제 버튼(도서 테이블)
        btnDeleteBooks = new JButton("도 서 삭 제");
        btnDeleteBooks.setFont(new Font("굴림", Font.PLAIN, 18));
        btnDeleteBooks.setBounds(618, 130, 138, 38);
        contentPane.add(btnDeleteBooks);

        // (대출 테이블)
        DefaultTableModel loanTableModel = new DefaultTableModel();
        loanTableModel.addColumn("대출 ID");
        loanTableModel.addColumn("회원 ID");
        loanTableModel.addColumn("도서 ID");
        loanTableModel.addColumn("대출일");
        loanTableModel.addColumn("반납일");

        loanTable = new JTable(loanTableModel);
        JScrollPane scrollPaneLoans = new JScrollPane(loanTable);
        scrollPaneLoans.setBounds(12, 209, 762, 70);
        contentPane.add(scrollPaneLoans);

        // 접속자 대출 조회(대출 테이블)
        showAllLoans = new JButton("대출 기록 조회");
        showAllLoans.setBounds(12, 169, 161, 30);
        contentPane.add(showAllLoans);

        // (회원 테이블)
        DefaultTableModel memberTableModel = new DefaultTableModel();
        memberTableModel.addColumn("사용자 ID");
        memberTableModel.addColumn("이름");
        memberTableModel.addColumn("비밀번호");
        memberTableModel.addColumn("연락처");
        memberTableModel.addColumn("주소");

        memberTable = new JTable(memberTableModel);
        JScrollPane scrollPaneMember = new JScrollPane(memberTable);
        scrollPaneMember.setBounds(12, 358, 762, 70);
        contentPane.add(scrollPaneMember);

        // 전체 회원 조회 버튼(회원 테이블)
        showAllMembers = new JButton("전체 회원 조회");
        showAllMembers.setBounds(12, 318, 161, 30);
        contentPane.add(showAllMembers);

        // 회원 수정 버튼(회원 테이블)
        btnMemberAlter = new JButton("회 원 수 정");
        btnMemberAlter.setFont(new Font("굴림", Font.PLAIN, 18));
        btnMemberAlter.setBounds(468, 438, 138, 38);
        contentPane.add(btnMemberAlter);

        // 회원 삭제 버튼(회원 테이블)
        btnMemberDelete = new JButton("회 원 삭 제");
        btnMemberDelete.setFont(new Font("굴림", Font.PLAIN, 18));
        btnMemberDelete.setBounds(618, 438, 138, 38);
        contentPane.add(btnMemberDelete);

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
        		
//        		if (selectedColumn.equals("isAvailable")) {
//        			boolean isAvailable = Boolean.parseBoolean(keyword);
//        		}
        		
        		List<BooksVO> vos = booksService.searchBooks(selectedColumn, keyword);
        		
        		// 테이블 모델 초기화
        		DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
        		model.setRowCount(0); // 기존 데이터 모두 제거
        		
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
        		 
        	    DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
        	    model.setRowCount(0); 
        		 
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
        
        // 도서 추가 버튼(도서 테이블)
        btnAddBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookTitle = JOptionPane.showInputDialog("도서 제목:");
                String bookAuthor = JOptionPane.showInputDialog("작가:");
                String bookPublisher = JOptionPane.showInputDialog("출판사:");
                String bookGenre = JOptionPane.showInputDialog("장르:");
                boolean isAvailable = true;

                BooksVO newBook = new BooksVO();
                newBook.setTitle(bookTitle);
                newBook.setAuthor(bookAuthor);
                newBook.setPublisher(bookPublisher);
                newBook.setGenre(bookGenre);
                newBook.setAvailable(isAvailable);

                BooksService booksService = new BooksService();
                boolean isSuccess = booksService.addBook(newBook);

                if (isSuccess) {
                    JOptionPane.showMessageDialog(null, "도서가 성공적으로 추가되었습니다.");
                } else {
                    JOptionPane.showMessageDialog(null, "도서 추가 중 오류가 발생했습니다.");
                }
            }
        });

        // 도서 삭제 버튼(도서 테이블)
        btnDeleteBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = bookTable.getSelectedRow();

                if (selectedRow >= 0) {
                    int bookID = (int) bookTable.getValueAt(selectedRow, 0);

                    int confirm = JOptionPane.showConfirmDialog(null, "선택한 도서를 삭제하시겠습니까?", "도서 삭제", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        // 사용자가 확인을 눌렀을 때만 도서 삭제
                        BooksService booksService = new BooksService();
                        boolean isSuccess = booksService.deleteBook(bookID);

                        if (isSuccess) {
                            JOptionPane.showMessageDialog(null, "도서가 성공적으로 삭제되었습니다.");
                            DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
                            model.removeRow(selectedRow);
                        } else {
                            JOptionPane.showMessageDialog(null, "도서 삭제 중 오류가 발생했습니다.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "도서를 선택하세요.", "오류", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 전체 대출 조회(대출 테이블)
        showAllLoans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoansService loansService = new LoansService();
                List<LoansVO> vos = loansService.showAdminLoans();

                // 테이블 모델 초기화
                DefaultTableModel model = (DefaultTableModel) loanTable.getModel();
                model.setRowCount(0); // 기존 데이터 모두 제거

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
        
        // 전체 회원 조회 버튼(회원 테이블)
        showAllMembers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 MemberService memberService = new MemberService();
			        List<MembersVO> vos = memberService.showAllMembers();

			        // 테이블 모델 초기화
			        DefaultTableModel model = (DefaultTableModel) memberTable.getModel();
			        model.setRowCount(0); // 기존 데이터 모두 제거

			        for (MembersVO vo : vos) {
			            model.addRow(new Object[] {
			                vo.getMemberID(),
			                vo.getName(),
			                vo.getPassword(),
			                vo.getContact(),
			                vo.getAddress(),
			            });
			        }
				
			}
		});
        
        // 회원 정보 수정 버튼(회원 테이블)
        btnMemberAlter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = memberTable.getSelectedRow();

                if (selectedRow >= 0) {
                	MemberService memberService = new MemberService();

                	int memberID = (int) memberTable.getValueAt(selectedRow, 0);
                    String name = (String) memberTable.getValueAt(selectedRow, 1);
                    String password = (String) memberTable.getValueAt(selectedRow, 2);
                    String contact = (String) memberTable.getValueAt(selectedRow, 3);
                    String address = (String) memberTable.getValueAt(selectedRow, 4);

                    String newName = JOptionPane.showInputDialog("새 이름:", name);
//                    String newName = name;
                    String newPassword = JOptionPane.showInputDialog("새 비밀번호:", password);
                    String newContact = JOptionPane.showInputDialog("새 연락처:", contact);
                    String newAddress = JOptionPane.showInputDialog("새 주소:", address);
                    
                    boolean isValid = memberService.newValidationCheck(newName, newPassword, newContact, newAddress);

//                    boolean isValid = true;
                    
    		        if (isValid) {
    		        	
    		        	System.out.println("update_debug: " + newName + newPassword + newContact + newAddress);

    		        	boolean isUpdated = memberService.updateMember(memberID, newName, newPassword, newContact, newAddress);
    		            
    		            
    		            if (isUpdated) {
    		                JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.", "회원수정 성공", JOptionPane.INFORMATION_MESSAGE);
    		            } else {
    		                JOptionPane.showMessageDialog(null, "수정에 실패하였습니다. 중복된 회원일 수 있습니다.", "회원수정 실패", JOptionPane.ERROR_MESSAGE);
    		            }
    		        } else {
    		            JOptionPane.showMessageDialog(null, "입력 정보가 유효하지 않습니다.", "유효성 검사 실패", JOptionPane.ERROR_MESSAGE);
    		        }
                    
                    
                }
            }
        });
        
        // 회원 정보 삭제(회원 테이블)
        btnMemberDelete.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
                int selectedRow = memberTable.getSelectedRow();
                
                if(selectedRow >= 0) {
                	int memberID = (int) memberTable.getValueAt(selectedRow, 0);
                	
                	int confirm = JOptionPane.showConfirmDialog(null, "선택한 회원(대출 기록)를 삭제하시겠습니까?", "회원 삭제", JOptionPane.YES_NO_OPTION);
                	 if (confirm == JOptionPane.YES_OPTION) {
                         // 사용자가 확인을 눌렀을 때만 도서 삭제
                		 MemberService memberService = new MemberService();
                		 boolean isSuccess = memberService.deleteMember(memberID);

                         if (isSuccess) {
                             JOptionPane.showMessageDialog(null, "회원(대출 기록)이 성공적으로 삭제되었습니다.");
                             DefaultTableModel model = (DefaultTableModel) memberTable.getModel();
                             model.removeRow(selectedRow);
                         } else {
                             JOptionPane.showMessageDialog(null, "회원 삭제 중 오류가 발생했습니다.");
                         }
                     }
                 } else {
                     JOptionPane.showMessageDialog(null, "회원를 선택하세요.", "오류", JOptionPane.ERROR_MESSAGE);
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
	}   
}
     
     
