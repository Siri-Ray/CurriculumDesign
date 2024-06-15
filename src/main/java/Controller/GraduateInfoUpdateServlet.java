package Controller;

import Dao.BasicInfoDao;
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

/**
 * @Author itmei
 * @Date 2024/6/12 20:14
 * @description:
 * @Title: GraduateInfoUpdateServlet
 * @Package Controller
 */
@WebServlet("/updateInfoEndpoint")
public class GraduateInfoUpdateServlet extends HttpServlet {



        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            basicInformation basicInformation = JsonUtil.parseJsonToEntity(request.getReader(), basicInformation.class);

            BasicInfoDao basicInfoDao = new BasicInfoDao();
            boolean success;

            try {
                int result = basicInfoDao.updateBasicInformation(basicInformation);
                success = result > 0;
            } catch (SQLException e) {
                success = false;
                e.printStackTrace(); // You may want to log this
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(new JsonResponse(success ? 1 : 0));
            out.print(jsonResponse);
            out.flush();
        }

        private class JsonResponse {
            private int success;

            public JsonResponse(int success) {
                this.success = success;
            }

            public int getSuccess() {
                return success;
            }
        }
    }
