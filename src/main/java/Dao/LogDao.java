package Dao;

import Models.Log;
import Utils.ConnectionPoolUtil;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author itmei
 * @Date 2024/6/12 20:12
 * @description:
 * @Title: LogDao
 * @Package Dao
 */
public class LogDao {
    public static void addLog(String username, String operation, String details) throws SQLException {
        String sql = "INSERT INTO operation_logs (username, operation, timestamp, details) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionPoolUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, operation);
            stmt.setTimestamp(3, Timestamp.from(Instant.now()));
            stmt.setString(4, details);
            stmt.executeUpdate();
        }
    }


    public List<Log> getAllLog() throws SQLException {
        List<Log> logList = new ArrayList<>();
        String sql = "SELECT * FROM operation_logs ORDER BY timestamp DESC";
        try (Connection conn = ConnectionPoolUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Log log = new Log(
                        rs.getInt("log_id"),
                        rs.getString("username"),
                        rs.getString("operation"),
                        rs.getTimestamp("timestamp"),
                        rs.getString("details")
                );
                logList.add(log);
            }
        }
        return logList;
    }

    public List<Log> searchByTypeAndKeyword(String type, String keyword) throws SQLException {
        List<Log> logList = new ArrayList<>();
        String sql = "SELECT * FROM operation_logs WHERE " + type + " LIKE ? ORDER BY timestamp DESC";
        try (Connection conn = ConnectionPoolUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Log log = new Log(
                            rs.getInt("log_id"),
                            rs.getString("username"),
                            rs.getString("operation"),
                            rs.getTimestamp("timestamp"),
                            rs.getString("details")
                    );
                    logList.add(log);
                }
            }
        }
        return logList;
    }
}
