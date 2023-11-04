package cn.net.nit.ems;

import java.sql.*;

/**
 * 数据库连接的工具类
 */
public class DBUtil {
    private static final String
//            链接数据库
            url="jdbc:mysql://localhost:3306/course?user=root&password=123456&serverTimezone=UTC";
    public static Connection getConnection(){
        Connection connection=null;
        try {
            connection= DriverManager.getConnection(url);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(connection!=null)
            return connection;
        else
            return null;
    }

    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {}
            conn = null;
        }
    }

    public static void close(Statement stmt, ResultSet rs) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {}
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {}
        }
    }
}