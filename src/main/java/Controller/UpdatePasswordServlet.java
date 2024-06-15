package Controller;

import Dao.GraduateStudentDao;
import Models.SP;
import Models.UP;
import Utils.JsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import static Utils.SM3Util.encrypt;

/**
 * @Author itmei
 * @Date 2024/6/12 20:13
 * @description:
 * @Title: UpdatePasswordServlet
 * @Package Controller
 */
@WebServlet("/changePasswordServlet")
public class UpdatePasswordServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        System.out.println("changing...");

        // 解析JSON数据
        SP sp = JsonUtil.parseJsonToEntity(request.getReader(), SP.class);
        String username = String.valueOf(sp.getStudentId());
        String newPassword = sp.getPassword();

        // 对密码进行加密（这里假设有一个加密函数encrypt）
        String encryptedPassword = encrypt(newPassword);

        // 更新密码并获取更新结果 dad37f8d519955403387583288a01779914677ff18c5e11e0888c3570cc47a96  3150001
        boolean success = false;
        String message = "";
        try {
            success = GraduateStudentDao.updatePassword(username, encryptedPassword);
            if (success) {
                message = "Password updated successfully.";
            } else {
                message = "Failed to update password.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            message = "Database error: " + e.getMessage();
        }

        // 构造JSON响应
        String jsonResponse = "{\"success\":\"" + (success ? "1" : "0") + "\",\"message\":\"" + message + "\"}";

        // 发送JSON响应
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }
}
