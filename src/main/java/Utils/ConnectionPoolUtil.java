package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * @Author itmei
 * @Date 2024/5/21 14:08
 * @description:
 * @Title: ConnectionPoolUtil
 * @Package com.util
 */
public class ConnectionPoolUtil {
    private static LinkedList<Connection> pool = new LinkedList<>();
    private static final int INITIAL_POOL_SIZE = 10;
    private static final String DB_URL = "jdbc:postgresql://192.168.65.131:26000/postgres";
    private static final String DB_USER = "opengaussuser";
    private static final String DB_PASSWORD = "openGauss@123";

    static {

        try {
            Class.forName("org.postgresql.Driver");
            for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
                pool.add(DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized Connection getConnection() {
        if (pool.isEmpty()) {
            try {
                return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pool.removeFirst();
    }

    public static synchronized void releaseConnection(Connection connection) {
        if (connection != null) {
            pool.addLast(connection);
        }
    }
}
