package t99_projectPractice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoansDAO {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public LoansDAO() {
        conn = DatabaseConnector.getConnection();
    }

    // pstmt객체 close
    public void pstmtClose() {
        try {
            if (pstmt != null)
                pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // rs객체 close
    public void rsClose() {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pstmtClose();
    }

    // 나머지 메소드들을 추가하여 로직을 구현합니다.
}
