package Controller;

import Dao.BasicInfoDao;
import Models.KT;
import Models.basicInformation;
import Utils.JsonUtil;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author itmei
 * @Date 2024/6/12 20:13
 * @description:
 * @Title: SearchGraduateServlet
 * @Package Controller
 */
@WebServlet("/searchGraduateServlet")
public class SearchGraduateServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        KT kt = JsonUtil.parseJsonToEntity(request.getReader(), KT.class);
        String keyword = kt.getKeyword();
        String type = kt.getType();

        BasicInfoDao basicInfoDao = new BasicInfoDao();
        List<basicInformation> infoList = new ArrayList<>();

        try {
            if (type.equals("all")) {
                infoList = basicInfoDao.getAllBasicInformation();

            } else if(type.equals("studentId")){
                infoList = basicInfoDao.searchByTypeAndKeyword("student_id", keyword);
            }
            else {
                infoList = basicInfoDao.searchByTypeAndKeyword(type, keyword);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("records", infoList);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String json = JsonUtil.toJson(result);
        response.getWriter().write(json);
    }
}
