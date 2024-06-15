package Controller;

import Dao.UserDao;
import Models.KT;
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
import java.util.List;

/**
 * @Author itmei
 * @Date 2024/6/12 20:16
 * @description:
 * @Title: UserSearchServlet
 * @Package Controller
 */
@WebServlet("/searchUsersServlet")
public class UserSearchServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        KT kt = JsonUtil.parseJsonToEntity(request.getReader(), KT.class);
        String keyword = kt.getKeyword();
        String type = kt.getType();

        List<User> users = null;
        UserDao userDao = new UserDao();

        try {
            if ("username".equals(type)) {
                users = userDao.searchUserByUsername(keyword);
            } else if ("user_role".equals(type)) {
                users = userDao.searchUserByUserRole(keyword);
            } else if ("college".equals(type)) {
                users = userDao.searchUserByCollege(keyword);
            } else if ("all".equals(type)) {
                users = userDao.searchAllUsers();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(users);
        out.print(jsonResponse);
        out.flush();
    }
}
