package Dao;

import Utils.ConnectionPoolUtil;
import Utils.OrmUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author itmei
 * @Date 2024/5/26 21:19
 * @description:
 * @Title: BaseDao
 * @Package Dao
 */
public class BaseDao {

    public static <T> List<T> select(String sql, Class<T> clazz, Object... params) {
        List<T> resultList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPoolUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            if(params.length>0) {
                for (int i = 0; i < params.length; i++) {
                    stmt.setObject(i + 1, params[i]);
                }
            }
            rs = stmt.executeQuery();

            resultList = OrmUtil.mapResultSetToList(rs, clazz);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResource(rs);
            closeResource(stmt);
            ConnectionPoolUtil.releaseConnection(conn);
        }

        return resultList;
    }

    public static int query(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rowsAffected = 0;

        try {
            conn = ConnectionPoolUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            if (params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    stmt.setObject(i + 1, params[i]);
                }
            }
            rowsAffected = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResource(stmt);
            ConnectionPoolUtil.releaseConnection(conn);
        }

        return rowsAffected;
    }


    public static void closeResource(AutoCloseable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
