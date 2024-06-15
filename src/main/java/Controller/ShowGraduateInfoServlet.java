package Controller;

import Dao.BasicInfoDao;
import Models.basicInformation;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * @Author itmei
 * @Date 2024/6/12 20:14
 * @description:
 * @Title: ShowGraduateInfoServlet
 * @Package Controller
 */
@WebServlet("/getBasicInfoEndpoint")
public class ShowGraduateInfoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("getting...");
        String studentId = request.getParameter("studentId");
        BasicInfoDao basicInfoDao = new BasicInfoDao();
        basicInformation basicInformationById = null;
        try {
            basicInformationById = basicInfoDao.getBasicInformationById(Integer.parseInt(studentId));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(basicInformationById);
        out.print(jsonResponse);
        out.flush();
    }
}
