package Controller;

import Dao.UserDao;
import Models.UP;
import Models.User;
import Utils.ConnectionPoolUtil;
import Utils.JsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Utils.SM3Util.encrypt;

/**
 * @Author itmei
 * @Date 2024/6/10 16:58
 * @description:
 * @Title: Controller.OtherRolesLoginServlet
 * @Package PACKAGE_NAME
 */
@WebServlet("/otherLoginServlet")
public class OtherRolesLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        System.out.println("log2");
        UP up = JsonUtil.parseJsonToEntity(request.getReader(), UP.class);
        String username = String.valueOf(up.getUsername());
        String password = encrypt(up.getPassword());

        UserDao userDao = new UserDao();
        // 调用 DAO 层的方法来验证用户信息

        try {


            List<User> userList = new ArrayList<>();
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

                    userList.add(user);
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

            if (userList != null && !userList.isEmpty()) {
                User user = userList.get(0);
                String userType = user.getUserRole(); // 获取用户类型

                // 返回成功响应
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"code\": 0, \"msg\": \"success\", \"userType\": \"" + userType + "\"}");
            } else {
                // 返回失败响应
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"code\": 1, \"msg\": \"Invalid username or password.\"}");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

