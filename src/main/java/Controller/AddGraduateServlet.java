package Controller;

import Dao.GraduateStudentDao;
import Dao.UserDao;
import Dao.VerifyDao;
import Models.User;
import Models.VerifyInfo;
import Models.graduateStudent;
import Utils.JsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.Console;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static Dao.GraduateStudentDao.searchGraduateStudentByNameAndPassword;
import static Utils.SM3Util.encrypt;

/**
 * @Author itmei
 * @Date 2024/6/12 20:15
 * @description:
 * @Title: AddGraduateServlet
 * @Package Controller
 */
@WebServlet("/addGraduateServlet")
public class AddGraduateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("logging...");
        VerifyInfo verifyInfo = JsonUtil.parseJsonToEntity(request.getReader(), VerifyInfo.class);


        // 调用 DAO 层的方法将用户信息添加到数据库中
        VerifyDao verifyDAO = new VerifyDao();
        int success = 0;
        try {
            success = verifyDAO.addVerifyInfo(verifyInfo);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            if (success > 0) {
                response.getWriter().write("{\"code\": 0, \"msg\": \"success\"}");
            } else {
                response.getWriter().write("{\"code\": 1, \"msg\": \"Failed to add pending review.\"}");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // 设置响应类型

    }

}

