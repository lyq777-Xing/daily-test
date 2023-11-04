package cn.net.nit.ems;
import java.sql.*;

/**
 * �������ݿ�JDBC����
 * @author Administrator
 */
public class MySQLDemo {

	public static void main(String[] args) throws Exception {
		// ָ���������ݿ���������������
//		���������mysql8�İ汾
		String driver = "com.mysql.cj.jdbc.Driver";
		// ��Ҫ���ʵ����ݿ�URL
		String url = "jdbc:mysql://localhost:3306/course";
		// ���ݿ���ʵ��û���
		String db_user = "root";
		// ���ݿ���ʵ�����
		String db_pwd = "123456";

		// Ҫִ�еĲ�ѯSQL���
		String querySql = "SELECT * FROM UserInfo";
		// Ҫִ�еĸ���SQL���
		String updateSql = "UPDATE UserInfo SET Cell='88229530' WHERE ID=?";
		
		// ����һ��Connection����,���ں����ݿ⽨������
		Connection conn = null;
		// ����һ��Statement�������ڶ����ݿ�ִ��SQL���
		Statement stmt = null;
		// Ԥ�������
		PreparedStatement ps = null;
		try {
			// װ����������
			Class.forName(driver);
			// ��ȡ�����ݿ������
			conn = DriverManager.getConnection(url, db_user, db_pwd);
			// ��ֹ�Զ��ݽ������ûع���
			conn.setAutoCommit(false);
			// ����һ��Statement�������ڶ����ݿ�ִ��SQL���
			stmt = conn.createStatement();
			// ִ�в�ѯ�������õ��Ľ�������ResultSet����
			ResultSet rs = stmt.executeQuery(querySql);
			// ��ӡ����ѯ������������
			System.out.println("ID\tLoginName\tUserName\tUserPwd");
			System.out.println("-------------------------------------------------");
			while (rs.next()) {
				int id = rs.getInt("ID");
				String loginName = rs.getString("LoginName");
				String userName = rs.getString("UserName");
				String passWord = rs.getString("UserPwd");
				System.out.println(id+"\t"+loginName+"\t\t"+userName+"\t\t"+passWord);
			}
			
			// Ԥ����SQL���
			ps = conn.prepareStatement(updateSql);
			// ���ò���
			ps.setInt(1,6);
			// �������ݿ�ļ�¼�����ظ��µļ�¼��
			int count = ps.executeUpdate();
			System.out.println("���¼�¼��"+count+"��");
			// ����ݽ�
			conn.commit();
		} catch (Exception e) {
			System.out.println("Error:"+e);
			try { // �������ɹ���ع�
				conn.rollback();
			} catch (Exception ee) {}
		} finally {
			try {
				if (conn!=null) conn.close();
				if (stmt!=null) stmt.close();
			} catch (SQLException e) {}
		}
	}
}
