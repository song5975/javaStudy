package t99_projectPractice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MembersDAO {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public MembersDAO() {
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

    public String getAdminPassword() {
        String adminPassword = null;

        try {
            String sql = "SELECT password FROM members WHERE name = 'ADMIN'";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                adminPassword = rs.getString("password");
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminPassword;
    }

    // 나머지 메소드들을 추가하여 로직을 구현합니다.
}
