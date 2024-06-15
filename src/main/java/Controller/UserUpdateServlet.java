package Controller;

import Dao.LogDao;
import Dao.UserDao;
import Models.User;
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

import static Utils.SM3Util.encrypt;

/**
 * @Author itmei
 * @Date 2024/6/12 20:16
 * @description:
 * @Title: UserUpdateServlet
 * @Package Controller
 */
@WebServlet("/editUserServlet")
public class UserUpdateServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("update...");
        User user = JsonUtil.parseJsonToEntity(request.getReader(), User.class);
        user.setPassword(encrypt(user.getPassword()));

        UserDao userDao = new UserDao();
        boolean success;

        try {
            int result = userDao.updateUser(user);
            success = result > 0;
            if(success==true){
                LogDao.addLog(String.valueOf(user.getUsername()),"update",user.toString());
            }
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
