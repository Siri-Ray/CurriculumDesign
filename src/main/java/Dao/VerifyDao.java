package Dao;

import Models.User;
import Models.VerifyInfo;
import Utils.ConnectionPoolUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Dao.BaseDao.query;
import static Dao.BaseDao.select;

/**
 * @Author itmei
 * @Date 2024/6/14 22:59
 * @description:
 * @Title: VerifyDao
 * @Package Dao
 */
public class VerifyDao {
    public int addVerifyInfo(VerifyInfo verifyInfo) throws SQLException {
        String sql = "INSERT INTO verify_information (name, gender, id_number, college, major, degree_type, supervisor) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return query(sql,verifyInfo.getName(),verifyInfo.getGender(),verifyInfo.getIdNumber(),verifyInfo.getCollege(),verifyInfo.getMajor(),verifyInfo.getDegreeType(),verifyInfo.getSupervisor());
    }

    public static int deleteById(int id) {
        String sql = "DELETE FROM verify_information WHERE review_id = ?";
        return query(sql,id);
    }

    public static VerifyInfo searchById(int id) throws SQLException {
        VerifyInfo verifyInfo = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPoolUtil.getConnection(); // 获取数据库连接的代码，略去这部分
            String sql = "SELECT review_id, name, gender, id_number, college, major, degree_type, supervisor " +
                    "FROM verify_information " +
                    "WHERE review_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                verifyInfo = new VerifyInfo();
                verifyInfo.setReviewId(rs.getInt("review_id"));
                verifyInfo.setName(rs.getString("name"));
                verifyInfo.setGender(rs.getString("gender"));
                verifyInfo.setIdNumber(rs.getString("id_number"));
                verifyInfo.setCollege(rs.getString("college"));
                verifyInfo.setMajor(rs.getString("major"));
                verifyInfo.setDegreeType(rs.getString("degree_type"));
                verifyInfo.setSupervisor(rs.getString("supervisor"));
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

        return verifyInfo;
    }

    public static List<VerifyInfo> searchVerifyInfo() throws SQLException {
        List<VerifyInfo> verifyInfoList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPoolUtil.getConnection();// 获取数据库连接的代码，略去这部分
            String sql = "SELECT review_id, name, gender, id_number, college, major, degree_type, supervisor FROM verify_information";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                VerifyInfo verifyInfo = new VerifyInfo();
                verifyInfo.setReviewId(rs.getInt("review_id"));
                verifyInfo.setName(rs.getString("name"));
                verifyInfo.setGender(rs.getString("gender"));
                verifyInfo.setIdNumber(rs.getString("id_number"));
                verifyInfo.setCollege(rs.getString("college"));
                verifyInfo.setMajor(rs.getString("major"));
                verifyInfo.setDegreeType(rs.getString("degree_type"));
                verifyInfo.setSupervisor(rs.getString("supervisor"));

                verifyInfoList.add(verifyInfo);
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

        return verifyInfoList;
    }

    public String generateStudentId(String collegeCode, String majorCode) throws SQLException {
        String countSql = "SELECT COUNT(*) AS total FROM graduate_students";
        try (PreparedStatement stmt = ConnectionPoolUtil.getConnection().prepareStatement(countSql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int totalStudents = rs.getInt("total");
                int newId = totalStudents + 1;
                return collegeCode + majorCode + String.format("%04d", newId);
            } else {
                return collegeCode + majorCode + "0001";
            }
        }
    }
}
