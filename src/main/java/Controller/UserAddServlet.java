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
 * @Date 2024/6/12 20:19
 * @description:
 * @Title: UserAddServlet
 * @Package Controller
 */
@WebServlet("/addUserServlet")
public class UserAddServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("adduser");
        User user = JsonUtil.parseJsonToEntity(request.getReader(), User.class);
        user.setPassword(encrypt(user.getPassword()));

        UserDao userDao = new UserDao();
        boolean success;
        String message;

        try {
            success = userDao.addUser(user);
            message = success ? "User added successfully" : "Failed to add user";
            if(success==true){
                LogDao.addLog(String.valueOf(user.getUsername()),"insert",user.toString());
            }
        } catch (SQLException e) {
            success = false;
            message = "Database error: " + e.getMessage();
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(new JsonResponse(success ? 1 : 0, message));
        out.print(jsonResponse);
        out.flush();
    }

    private class JsonResponse {
        private int success;
        private String message;

        public JsonResponse(int success, String message) {
            this.success = success;
            this.message = message;
        }

        public int getSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}
