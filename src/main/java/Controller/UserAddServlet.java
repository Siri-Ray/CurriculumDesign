package Controller;

import Models.User;
import Utils.JsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @Author itmei
 * @Date 2024/6/12 20:19
 * @description:
 * @Title: UserAddServlet
 * @Package Controller
 */
@WebServlet("/addUserServlet")
public class UserAddServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        User user = JsonUtil.parseJsonToEntity(request.getReader(), User.class);


    }
}
