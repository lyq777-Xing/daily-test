package cn.net.nit.ems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lyq
 * @time 2023/11/4 21:40
 */
public class DeptDAO {
    Connection connection;
    /**
     * 查询所有部门信息（层级为顶级以下）
     * @return
     */
    public List<Department> getAllDepts() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Department> departments = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            if (connection == null)
                throw new SQLException("DeptDAO: 无法获得数据库连接!");
            String sql = " SELECT DeptID, DeptName, ParentID, Level, EntranceYear FROM Department WHERE Level > 0";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Department dept = new Department();
                dept.setId(rs.getInt("DeptID"));
                dept.setName(rs.getString("DeptName"));
                dept.setParentId(rs.getInt("ParentID"));
                dept.setLevel(rs.getInt("Level"));
                dept.setEntranceYear(rs.getString("EntranceYear"));
                departments.add(dept);
            }
        } catch (Exception ex) {
            System.out.println("UserDAO SQLException: " + ex);
        } finally {
            DBUtil.close(connection);
            DBUtil.close(ps, rs);
        }
        return departments;
    }
}
