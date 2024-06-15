package Controller;

import Dao.UserDao;
import Dao.VerifyDao;
import Models.User;
import Models.VerifyInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Utils.JsonUtil.toJson;
import static Utils.OrmUtil.getEntity;

/**
 * @Author itmei
 * @Date 2024/6/12 20:15
 * @description:
 * @Title: VerifyServlet
 * @Package Controller
 */
@WebServlet("/getPendingReviewsServlet")
public class VerifyServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        System.out.println("reviewing...");
        VerifyDao verifyDao = new VerifyDao();
        try {
            List<VerifyInfo> verifyInfoList = verifyDao.searchVerifyInfo();
            Map<String, Object> result = new HashMap<>();
            result.put("items", verifyInfoList);
            String json = toJson(result);
            response.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"code\": 1, \"msg\": \"Error: " + e.getMessage() + "\"}");
        }
    }
}
