package Controller;

import Dao.BasicInfoDao;
import Dao.LogDao;
import Models.KT;
import Models.Log;
import Models.basicInformation;
import Utils.JsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author itmei
 * @Date 2024/6/12 20:19
 * @description:
 * @Title: LogSearchServlet
 * @Package Controller
 */
@WebServlet("/fetchLogsServlet")
public class LogSearchServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        System.out.println("logs...");
        KT kt = JsonUtil.parseJsonToEntity(request.getReader(), KT.class);
        String keyword = kt.getKeyword();
        String type = kt.getType();

        LogDao logDao = new LogDao();

        List<Log> logList = new ArrayList<>();

        try {
            if (type.equals("all")) {
                logList = logDao.getAllLog();

            }
            else {
                logList = logDao.searchByTypeAndKeyword(type, keyword);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("logs", logList);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String json = JsonUtil.toJson(result);
        response.getWriter().write(json);

    }
}
