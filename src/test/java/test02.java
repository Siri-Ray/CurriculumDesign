import Dao.GraduateStudentDao;
import Dao.UserDao;
import Models.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author itmei
 * @Date 2024/6/15 5:44
 * @description:
 * @Title: test02
 * @Package PACKAGE_NAME
 */
public class test02 {
    public static void main(String[] args) throws SQLException {
        UserDao userDao = new UserDao();
        // 调用 DAO 层的方法来验证用户信息
        List<User> userList = null;

        userList = userDao.searchUserByNameAndPassword("1", "207cf410532f92a47dee245ce9b11ff71f578ebd763ev3bbea44ebd043d018fb");

        System.out.println(userList);}

}
