package cn.net.nit.ems.dao;

import cn.net.nit.ems.Util;
import cn.net.nit.ems.domain.User;
import cn.net.nit.ems.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	Connection connection;

//	connection.close方法 应当在调用完后使用，否则会报java.sql.SQLNonTransientConnectionException: No operations allowed after connection closed.
//	解决办法：每次方法都重新链接一次
	/**
	 * 查询用户
	 * @param loginName
	 * @param passwd
	 * @return
	 */
	public User findUser(String loginName, String passwd)
	{
		connection = DBUtil.getConnection();
		User user = new User();
		PreparedStatement p = null;
		String sql = "SELECT u.*, d.DeptName FROM UserInfo u LEFT JOIN Department d ON u.DeptID = d.DeptID WHERE u.LoginName=?";
//		防止sql注入攻击
		if (passwd != null) {
			sql += " AND UserPwd=?";
		}
		try {
			if (connection == null) throw new Exception("无法获得数据库连接!");
			p = connection.prepareStatement(sql);
			p.setString(1, loginName);
			if (passwd != null) {
				p.setString(2, passwd);
			}
			ResultSet resultSet = p.executeQuery();
			if (resultSet.next()) {
				user.setUserId(resultSet.getInt("ID"));
				user.setUserName(resultSet.getString("UserName"));
				user.setLoginName(resultSet.getString("LoginName"));
				user.setSex(resultSet.getString("Sex").charAt(0));
				user.setCell(resultSet.getString("Cell"));
				user.setEmail(resultSet.getString("Email"));
				user.setDeptId(resultSet.getString("DeptID"));
				user.setDeptName(resultSet.getString("DeptName"));
			}else {
				return null;
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return user;
	}

	/**
	 * 添加用户
	 * @param user
	 * @param role
	 * @return
	 */
	public boolean insert(User user, String role) {
		connection = DBUtil.getConnection();
		String sql = " INSERT INTO UserInfo(LoginName, UserName, UserPwd, UserCode, Sex, DeptID, Cell, Email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement = null;
		try {
			if (connection == null) throw new Exception("无法获得数据库连接!");
//			添加用户
			preparedStatement = connection.prepareStatement(sql);
			int num = 1;
			preparedStatement.setString(num++, user.getLoginName());
			preparedStatement.setString(num++, user.getUserName());
			preparedStatement.setString(num++, user.getPassword());
			preparedStatement.setString(num++, user.getUserCode());
			preparedStatement.setString(num++, user.getSex() + "");
			preparedStatement.setString(num++, user.getDeptId());
			preparedStatement.setString(num++, user.getCell());
			preparedStatement.setString(num++, user.getEmail());
			int i = preparedStatement.executeUpdate();
			if(i != 1){
				throw new Exception("插入表UserInfo出错！");
			}
//			添加用户角色关联表数据
			sql = "INSERT INTO RoleMember(UserID, RoleID) VALUES((SELECT LAST_INSERT_ID()), ?)";
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
			preparedStatement1.setString(1,role);
			int i1 = preparedStatement1.executeUpdate();
			if(i1 != 1){
				throw new Exception("插入表RoleMember出错！");
			}
//			connection.commit();
			return true;
		} catch (SQLException e) {
			System.err.println("****插入表UserInfo出错！");
			e.printStackTrace();
			try {
				if (connection != null)  connection.rollback();
			} catch (Exception ex) { }
			return false;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			if (connection != null) {
				DBUtil.close(connection);
			}
		}
	}

	/**
	 * 获取用户的的所有角色
	 * @param userId
	 * @return
	 */
	public List<Integer> getRoles(int userId) {
		connection = DBUtil.getConnection();
		String sql = "SELECT RoleID FROM RoleMember WHERE UserId = ?";
		List<Integer> roles = new ArrayList<Integer>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			if (connection == null) throw new Exception("UserDAO.getRoles: 无法获得数据库连接!");
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
			preparedStatement1.setInt(1, userId);
			resultSet = preparedStatement1.executeQuery();
			while (resultSet.next()) {
				int roleId = resultSet.getInt("RoleID");
				roles.add(roleId);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(connection);
			DBUtil.close(preparedStatement, resultSet);
		}
		return roles;
	}

	/**
	 * 根据姓名查询用户
	 * @param userName
	 * @return
	 */
	public List<User> queryByName(String userName) {
		List<User> users = new ArrayList<User>();
		String sql = " SELECT u.ID, u.UserCode, u.LoginName, u.UserName, u.Sex, u.EntranceYear,u.DeptID, d.DeptName, u.Cell"
				+ " FROM UserInfo u LEFT JOIN Department d ON u.DeptID = d.DeptID "
				+ " WHERE UserName LIKE ? ORDER BY u.UserCode";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DBUtil.getConnection();
			if (connection == null) throw new Exception("UserDAO: 无法获得数据库连接!");
			ps = connection.prepareStatement(sql);
			ps.setString(1, "%" + userName + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("ID"));
				user.setUserCode(rs.getString("UserCode"));
				user.setLoginName(rs.getString("LoginName"));
				user.setUserName(rs.getString("UserName"));
				user.setSex(rs.getString("Sex").charAt(0));
				user.setEntranceYear(rs.getString("EntranceYear"));
				user.setDeptId(rs.getString("DeptID"));
				user.setDeptName(rs.getString("DeptName"));
				user.setCell(rs.getString("Cell"));
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(connection);
			DBUtil.close(ps, rs);
		}
		return users;
	}

	/**
	 * 根据id查询用户信息
	 * @param userId
	 * @return
	 */
	public User getUserById(int userId) {
		String sql = "SELECT u.*, d.DeptName FROM UserInfo u LEFT JOIN Department d ON u.DeptID = d.DeptID WHERE u.ID=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DBUtil.getConnection();
			if (connection == null)
				throw new SQLException("UserDAO: 无法获得数据库连接!");
			ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("ID"));
				user.setLoginName(rs.getString("LoginName"));
				user.setUserName(rs.getString("UserName"));
				user.setPassword(rs.getString("UserPwd"));
				user.setUserCode(rs.getString("UserCode"));
				user.setSex(rs.getString("Sex").charAt(0));
				user.setCell(rs.getString("Cell"));
				user.setEmail(rs.getString("Email"));
				user.setParentEmail(rs.getString("ParentEmail"));
				user.setDeptId(rs.getString("DeptID"));
				user.setDeptName(rs.getString("DeptName"));
				user.setEntranceYear(rs.getString("EntranceYear"));
				user.setIntroduce(rs.getString("Introduce"));
				return user;
			}
		} catch (SQLException ex) {
			System.out.println("UserDAO SQLException: " + ex);
		} finally {
			DBUtil.close(connection);
			DBUtil.close(ps, rs);
		}
		return null;
	}

	/**
	 * 更新用户信息（暂不修改用户角色）
	 * @param user
	 * @return
	 */
	public boolean update(User user) {
		String sql = " Update UserInfo SET UserName=?, UserPwd=?, UserCode=?, Sex=?, Email=?, "
				+ " ParentEmail=?, Cell=?, DeptId=?, EntranceYear=?, Introduce=?"
				+ " WHERE ID=?";
		PreparedStatement ps = null;
		try {
			connection = DBUtil.getConnection();
			if (connection == null)
				throw new Exception("UserDAO: 无法获得数据库连接!");
			ps = connection.prepareStatement(sql);
			int num = 1;
			ps.setString(num++, user.getUserName());
			ps.setString(num++, user.getPassword());
			ps.setString(num++, user.getUserCode());
			ps.setString(num++, user.getSex() + "");
			ps.setString(num++, user.getEmail());
			ps.setString(num++, user.getParentEmail());
			ps.setString(num++, user.getCell());
			ps.setString(num++, user.getDeptId());
			ps.setString(num++, user.getEntranceYear());
			ps.setString(num++, user.getIntroduce());
			ps.setInt(num++, user.getUserId());
			if (ps.executeUpdate() < 1) {
				throw new Exception("表UserInfo更新失败！");
			}
			connection.commit();
			return true;
		} catch (Exception e) {
			Util.Log("****表UserInfo更新失败！");
			e.printStackTrace();
			try {
				if (connection != null) connection.rollback();
			} catch(Exception ex) {}
			return false;
		} finally {
			DBUtil.close(connection);
			DBUtil.close(ps,null);
		}
	}

	public boolean delete(int userId){
		String sql = "DELETE FROM UserInfo WHERE UserID=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DBUtil.getConnection();
			if (connection == null)
				throw new Exception("UserDAO: 无法获得数据库连接!");
			ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);
			if (ps.executeUpdate() < 1) {
				throw new Exception("表UserInfo删除记录失败！");
			}
			sql = " DELETE FROM RoleMember WHERE UserID=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);
			if (ps.executeUpdate() < 1) {
				throw new Exception("表UserInfo删除记录失败！");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return true;
	}
}
