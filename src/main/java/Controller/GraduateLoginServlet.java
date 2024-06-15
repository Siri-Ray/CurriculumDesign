package Controller;

import Dao.GraduateStudentDao;
import Models.ReviewInfo;
import Models.UP;
import Models.graduateStudent;
import Utils.JsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.invoke.VarHandle;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static Utils.SM3Util.encrypt;

/**
 * @Author itmei
 * @Date 2024/6/10 16:57
 * @description:
 * @Title: Controller.GraduateLoginServlet
 * @Package PACKAGE_NAME
 */
@WebServlet("/graduateLoginServlet")
public class GraduateLoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取登录表单提交的用户名和密码
        UP up = JsonUtil.parseJsonToEntity(request.getReader(), UP.class);
        String username = String.valueOf(up.getUsername());
        String password = encrypt(up.getPassword());

        // 调用 DAO 层的方法来验证用户信息
        List<graduateStudent> graduateStudentList = null;
        try {
            GraduateStudentDao graduateStudentDao = new GraduateStudentDao();
            graduateStudentList = graduateStudentDao.searchGraduateStudentByNameAndPassword(username, password);
            // 如果用户列表不为空，说明验证通过，否则验证失败
            if (graduateStudentList != null && !graduateStudentList.isEmpty()) {
                graduateStudent student = graduateStudentList.get(0);
                Timestamp lastPasswordChangeTime = student.getLastPasswordChangeTime();
                Timestamp lastLoginAttemptTime = student.getLastLoginAttemptTime();
                int loginAttemptCount = student.getLoginAttemptCount();

                // Check if login attempt count exceeds the limit
                if (loginAttemptCount >= 5) {
                    // Update login attempt time and count
                    graduateStudentDao.updateLoginAttempt(username, loginAttemptCount + 1);

                    response.getWriter().write("{\"code\": 2, \"msg\": \"Account locked due to too many failed login attempts.\"}");
                    return;
                }

                // Reset login attempt count on successful login
                graduateStudentDao.resetLoginAttempt(username);

                // Check for first login or password change requirement
                StringBuilder msg = new StringBuilder("success");
                long currentTime = System.currentTimeMillis();

                if (lastPasswordChangeTime == null) {
                    // First login, password needs to be changed
                    response.getWriter().write("{\"code\": 3, \"msg\": \"First login, please change your password.\"}");
                    return;
                } else {
                    long lastPasswordChange = lastPasswordChangeTime.getTime();
                    long timeDifference = (currentTime - lastPasswordChange) / (1000 * 60 * 60 * 24); // in days
                    if (timeDifference > 90) {
                        // Password not changed in the last 90 days
                        response.getWriter().write("{\"code\": 4, \"msg\": \"Password not changed for over 90 days, please change your password.\"}");
                        return;
                    }
                }

                // Check recent failed login attempts within last 30 minutes
                if (lastLoginAttemptTime != null) {
                    long lastAttemptTime = lastLoginAttemptTime.getTime();
                    long timeDifference = (currentTime - lastAttemptTime) / (1000 * 60); // in minutes
                    if (timeDifference < 30 && loginAttemptCount > 0) {
                        msg.append(", you have attempted to login ").append(loginAttemptCount).append(" times in the last 30 minutes.");
                    }
                }
                response.getWriter().write("{\"code\": 0, \"msg\": \"" + msg.toString() + "\", \"userType\": \"" + "graduate"+ "\"}");

            } else {
                // Update login attempt time and count on failed login
                graduateStudent student = graduateStudentDao.searchGraduateStudentByUsername(username);
                if (student != null) {
                    graduateStudentDao.updateLoginAttempt(username, student.getLoginAttemptCount() + 1);
                    int failedAttempts = student.getLoginAttemptCount() + 1;
                    response.getWriter().write("{\"code\": 1, \"msg\": \"Invalid username or password.\", \"failedAttempts\": " + failedAttempts + "}");
                } else {
                    response.getWriter().write("{\"code\": 1, \"msg\": \"Invalid username or password.\"}");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
