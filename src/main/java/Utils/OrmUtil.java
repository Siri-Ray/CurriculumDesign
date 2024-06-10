package Utils;

import jakarta.servlet.http.HttpServletRequest;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author itmei
 * @Date 2024/5/21 14:07
 * @description:
 * @Title: OrmUtil
 * @Package com.util
 */
public class OrmUtil {

    //直接从result中封装对象
    public static <T> List<T> mapResultSetToList(ResultSet rs, Class<T> clazz) throws Exception {
        List<T> resultList = new ArrayList<>();

        while (rs.next()) {
            T entity = clazz.getDeclaredConstructor().newInstance();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnLabel(i);
                Object columnValue = rs.getObject(i);

                String fieldName = toCamelCase(columnName);

                Field field;
                try {
                    field = clazz.getDeclaredField(columnName);
                } catch (NoSuchFieldException e) {
                    // 如果实体类中没有该字段，跳过
                    continue;
                }

                field.setAccessible(true);

                // 处理类型转换
                if (field.getType() == double.class || field.getType() == Double.class) {
                    if (columnValue instanceof BigDecimal) {
                        columnValue = ((BigDecimal) columnValue).doubleValue();
                    }
                } else if (field.getType() == int.class || field.getType() == Integer.class) {
                    if (columnValue instanceof Number) {
                        columnValue = ((Number) columnValue).intValue();
                    }
                } else if (field.getType() == long.class || field.getType() == Long.class) {
                    if (columnValue instanceof Number) {
                        columnValue = ((Number) columnValue).longValue();
                    }
                } else if (field.getType() == float.class || field.getType() == Float.class) {
                    if (columnValue instanceof BigDecimal) {
                        columnValue = ((BigDecimal) columnValue).floatValue();
                    }
                }

                // 将值设置到实体对象
                field.set(entity, columnValue);
            }
            resultList.add(entity);
        }

        return resultList;
    }

    //工具方法
    public static String toCamelCase(String name) {
        StringBuilder result = new StringBuilder();
        boolean toUpperCase = false;

        for (char ch : name.toCharArray()) {
            if (ch == '_') {
                toUpperCase = true;
            } else if (toUpperCase) {
                result.append(Character.toUpperCase(ch));
                toUpperCase = false;
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    //直接从request中封装对象
    public static <T> T getEntity(HttpServletRequest request, Class<T> clazz) throws InstantiationException, IllegalAccessException {
        T entity = clazz.newInstance();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String paramName = field.getName();
            String paramValue = request.getParameter(paramName);
            if (paramValue != null) {
                try {
                    if (field.getType() == int.class) {
                        field.set(entity, Integer.parseInt(paramValue));
                    } else if (field.getType() == double.class) {
                        field.set(entity, Double.parseDouble(paramValue));
                    } else {
                        field.set(entity, paramValue);
                    }
                } catch (IllegalAccessException e) {
                    // handle exception
                }
            }
        }
        return entity;
    }
}
