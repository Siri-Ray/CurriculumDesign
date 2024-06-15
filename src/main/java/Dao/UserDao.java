package Dao;

import Models.User;
import Utils.ConnectionPoolUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Dao.BaseDao.select;

/**
 * @Author itmei
 * @Date 2024/6/12 20:10
 * @description:
 * @Title: UserDao
 * @Package Dao
 */
public class UserDao {
    public List<User> searchUserByUsername(String username) throws SQLException {
        return searchUser("username", username);
    }

    public List<User> searchUserByUserRole(String userRole) throws SQLException {
        return searchUser("user_role", userRole);
    }

    public List<User> searchUserByCollege(String college) throws SQLException {
        return searchUser("college", college);
    }

    public List<User> searchAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPoolUtil.getConnection();
            String sql = "SELECT * FROM administrators";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUsername(Integer.parseInt(rs.getString("username")));
                user.setPassword(rs.getString("password"));
                user.setUserRole(rs.getString("user_role"));
                user.setCollege(rs.getString("college"));

                users.add(user);
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

        return users;
    }

    private List<User> searchUser(String column, String value) throws SQLException {
        List<User> users = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPoolUtil.getConnection();
            String sql = "SELECT * FROM administrators WHERE " + column + " = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, value);
            rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUsername(Integer.parseInt(rs.getString("username")));
                user.setPassword(rs.getString("password"));
                user.setUserRole(rs.getString("user_role"));
                user.setCollege(rs.getString("college"));

                users.add(user);
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

        return users;
    }
    public static List<User> searchUserByNameAndPassword(String username, String password) throws SQLException {
        List<User> users = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPoolUtil.getConnection(); // 获取数据库连接的代码
            String sql = "SELECT * FROM administrators WHERE username = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUsername(Integer.parseInt(rs.getString("username")));
                user.setPassword(rs.getString("password"));
                user.setUserRole(rs.getString("user_role"));
                user.setCollege(rs.getString("college"));

                users.add(user);
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

        return users;
    }


}
