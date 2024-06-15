package Dao;

import Models.basicInformation;
import Utils.ConnectionPoolUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author itmei
 * @Date 2024/6/12 20:11
 * @description:
 * @Title: BasicInfoDao
 * @Package Dao
 */
public class BasicInfoDao {
    public int addBasicInformation(basicInformation info) throws SQLException {
        String sql = "INSERT INTO basic_information (student_id, name, gender, id_number, college, major, degree_type, supervisor) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionPoolUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, info.getStudentId());
            stmt.setString(2, info.getName());
            stmt.setString(3, info.getGender());
            stmt.setString(4, info.getIdNumber());
            stmt.setString(5, info.getCollege());
            stmt.setString(6, info.getMajor());
            stmt.setString(7, info.getDegreeType());
            stmt.setString(8, info.getSupervisor());
            return stmt.executeUpdate();
        }
    }
    public basicInformation getBasicInformationById(int studentId) throws SQLException {
        String sql = "SELECT * FROM basic_information WHERE student_id = ?";
        try (Connection conn = ConnectionPoolUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new basicInformation(
                            rs.getInt("student_id"),
                            rs.getString("name"),
                            rs.getString("gender"),
                            rs.getString("id_number"),
                            rs.getString("college"),
                            rs.getString("major"),
                            rs.getString("degree_type"),
                            rs.getString("supervisor")
                    );
                }
            }
        }
        return null;
    }

    // Update
    public int updateBasicInformation(basicInformation info) throws SQLException {
        String sql = "UPDATE basic_information SET name = ?, gender = ?, id_number = ?, college = ?, major = ?, degree_type = ?, supervisor = ? WHERE student_id = ?";
        try (Connection conn = ConnectionPoolUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, info.getName());
            stmt.setString(2, info.getGender());
            stmt.setString(3, info.getIdNumber());
            stmt.setString(4, info.getCollege());
            stmt.setString(5, info.getMajor());
            stmt.setString(6, info.getDegreeType());
            stmt.setString(7, info.getSupervisor());
            stmt.setInt(8, info.getStudentId());
            return stmt.executeUpdate();
        }
    }

    // Delete
    public int deleteBasicInformationById(int studentId) throws SQLException {
        String sql = "DELETE FROM basic_information WHERE student_id = ?";
        try (Connection conn = ConnectionPoolUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            return stmt.executeUpdate();
        }
    }

    // List all
    public List<basicInformation> getAllBasicInformation() throws SQLException {
        List<basicInformation> infoList = new ArrayList<>();
        String sql = "SELECT * FROM basic_information";
        try (Connection conn = ConnectionPoolUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                basicInformation info = new basicInformation(
                        rs.getInt("studentId"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("idNumber"),
                        rs.getString("college"),
                        rs.getString("major"),
                        rs.getString("degreeType"),
                        rs.getString("supervisor")
                );
                infoList.add(info);
            }
        }
        return infoList;
    }

    public List<basicInformation> searchByTypeAndKeyword(String type, String keyword) throws SQLException {
        List<basicInformation> infoList = new ArrayList<>();
        String sql = "SELECT * FROM basic_information WHERE " + type + " LIKE ?";

        try (Connection conn = ConnectionPoolUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    basicInformation info = new basicInformation(
                            rs.getInt("studentId"),
                            rs.getString("name"),
                            rs.getString("gender"),
                            rs.getString("idNumber"),
                            rs.getString("college"),
                            rs.getString("major"),
                            rs.getString("degreeType"),
                            rs.getString("supervisor")
                    );
                    infoList.add(info);
                }
            }
        }
        return infoList;
    }
}
