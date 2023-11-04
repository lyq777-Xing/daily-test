package cn.net.nit.ems;

import java.util.ArrayList;
import java.util.List;

public class User {
	/** 用户ID */
	private int userId;

	/** 登录帐户名 */
	private String loginName;

	/** 真实姓名 */
	private String userName;

	/** 学号或工号 */
	private String userCode;
	
	/** 性别：'1'-男；'0'-女 */
	private char sex;

	private String password;

	/** 班级或部门编号 */
	private String deptId;

	/** 手机号码 */
	private String cell;

	private String email;
	
	/** 角色："admin"-管理员;"user"-普通用户 */
//	private String role;

	/** 角色："10"-学生；"20"-教师；"30"-管理员 */
	private List<Integer> roles = new ArrayList<Integer>();

	/** 班级或部门名称 */
	private String deptName;

	/** 父母Email */
	private String parentEmail;

	/** 入学年份 */
	private String entranceYear;

	/** 个人介绍 */
	private String introduce;

	/** 是否可用: '0'-不可用；'1'-可用 */
	private char isActive;

	/**
	 * 判断该用户是否具有某一个角色
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
