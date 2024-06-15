package Dao;

import Models.User;
import Models.graduateStudent;
import Utils.ConnectionPoolUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Dao.BaseDao.query;
import static Dao.BaseDao.select;

/**
 * @Author itmei
 * @Date 2024/6/12 20:10
 * @description:
 * @Title: GraduateStudentDao
 * @Package Dao
 */
public class GraduateStudentDao {
    public static List<graduateStudent> searchGraduateStudentByNameAndPassword(String username, String password) throws SQLException {
        List<graduateStudent> students = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPoolUtil.getConnection(); // 获取数据库连接的代码

            String sql = "SELECT * FROM graduate_students WHERE student_id = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            while (rs.next()) {
                graduateStudent student = new graduateStudent();
                student.setStudentId(Integer.parseInt(rs.getString("student_id")));
                student.setPassword(rs.getString("password"));
                student.setLastPasswordChangeTime(rs.getTimestamp("last_password_change_time"));
                student.setLastLoginAttemptTime(rs.getTimestamp("last_login_attempt_time"));
                student.setLoginAttemptCount(rs.getInt("login_attempt_count"));

                students.add(student);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return students;
    }

    public void updateLoginAttempt(String username, int loginAttemptCount) throws SQLException {
        // Update the last login attempt time and login attempt count in the database
        String updateQuery = "UPDATE graduate_students SET last_login_attempt_time = CURRENT_TIMESTAMP, login_attempt_count = ? WHERE student_id = ?";

        try (Connection conn = ConnectionPoolUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
            stmt.setInt(1, loginAttemptCount);
            stmt.setString(2, username);
            stmt.executeUpdate();
        }
    }

    public void resetLoginAttempt(String username) throws SQLException {
        // Reset the login attempt count in the database
        String resetQuery = "UPDATE graduate_students SET login_attempt_count = 0 WHERE student_id = ?";

        try (Connection conn = ConnectionPoolUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(resetQuery)) {
            stmt.setString(1, username);
            stmt.executeUpdate();
        }
    }

    public graduateStudent searchGraduateStudentByUsername(String username) throws SQLException {
        // Query to find the graduate student by username
        String query = "SELECT * FROM graduate_students WHERE student_id = ?";

        try (Connection conn = ConnectionPoolUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new graduateStudent(
                            rs.getString("student_id"),
                            rs.getString("password"),
                            rs.getTimestamp("last_password_change_time"),
                            rs.getTimestamp("last_login_attempt_time"),
                            rs.getInt("login_attempt_count")
                    );
                }
            }
        }
        return null; // 如果没有找到，返回 null
    }


    public int addgraduateStudent(graduateStudent graduateStudent)  throws SQLException{
        String sql = "INSERT INTO graduate_students (student_id, password) VALUES (?, ?)";
        try (Connection conn = ConnectionPoolUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, graduateStudent.getStudentId());
            stmt.setString(2, graduateStudent.getPassword());
            return stmt.executeUpdate();
        }
    }

    public static boolean updatePassword(String username, String newPassword) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean updated = false;

        try {
            conn = ConnectionPoolUtil.getConnection();
            // 1. 更新密码
            String sqlUpdatePassword = "UPDATE graduate_students SET password = ? WHERE student_id = ?";
            stmt = conn.prepareStatement(sqlUpdatePassword);
            stmt.setString(1, newPassword);
            stmt.setString(2, username);
            int rowsUpdated = stmt.executeUpdate();
            stmt.close(); // 关闭之前的 PreparedStatement

            // 2. 更新 last_password_change_time 字段
            if (rowsUpdated > 0) {
                String sqlUpdateLastChangeTime = "UPDATE graduate_students SET last_password_change_time = ? WHERE student_id = ?";
                stmt = conn.prepareStatement(sqlUpdateLastChangeTime);
                stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
                stmt.setString(2, username);
                int rowsUpdatedTime = stmt.executeUpdate();
                if (rowsUpdatedTime > 0) {
                    updated = true;
                }
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return updated;
    }
}
