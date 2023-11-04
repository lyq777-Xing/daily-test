package cn.net.nit.ems;

import java.util.ArrayList;
import java.util.List;

public class User {
	/** �û�ID */
	private int userId;

	/** ��¼�ʻ��� */
	private String loginName;

	/** ��ʵ���� */
	private String userName;

	/** ѧ�Ż򹤺� */
	private String userCode;
	
	/** �Ա�'1'-�У�'0'-Ů */
	private char sex;

	private String password;

	/** �༶���ű�� */
	private String deptId;

	/** �ֻ����� */
	private String cell;

	private String email;
	
	/** ��ɫ��"admin"-����Ա;"user"-��ͨ�û� */
//	private String role;

	/** ��ɫ��"10"-ѧ����"20"-��ʦ��"30"-����Ա */
	private List<Integer> roles = new ArrayList<Integer>();

	/** �༶�������� */
	private String deptName;

	/** ��ĸEmail */
	private String parentEmail;

	/** ��ѧ��� */
	private String entranceYear;

	/** ���˽��� */
	private String introduce;

	/** �Ƿ����: '0'-�����ã�'1'-���� */
	private char isActive;

	/**
	 * �жϸ��û��Ƿ����ĳһ����ɫ
	 * @param roleId
	 * @return
	 */
	public boolean isMemberOfRole(int roleId) {
		boolean isMemberOf = false;
		if (this.roles != null) {
			isMemberOf = roles.contains(roleId);
		}
		return isMemberOf;
	}

	public String getParentEmail() {
		return parentEmail;
	}

	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}

	public String getEntranceYear() {
		return entranceYear;
	}

	public void setEntranceYear(String entranceYear) {
		this.entranceYear = entranceYear;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public char getIsActive() {
		return isActive;
	}

	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<Integer> getRoles() {
		return roles;
	}

	public void setRoles(List<Integer> roles) {
		this.roles = roles;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getCell() {
		return cell;
	}
	public void setCell(String cell) {
		this.cell = cell;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
