package cn.net.nit.ems;

import cn.net.nit.ems.dao.UserDAO;
import cn.net.nit.ems.domain.User;
import cn.net.nit.ems.util.DataInput;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;

/**
 * 教学管理系统主类
 * @author Rainkee Liu
 *
 */
public class EMSApp {

	public static void main(String[] args) {
		
		System.out.println("*******************************************");
		System.out.println("***                                     ***");
		System.out.println("***          教务管理系统   v0.1           ***");
		System.out.println("***                                     ***");
		System.out.println("*******************************************");
		String loginName, password;
		System.out.print("用户名:");
		Scanner scanner = new Scanner(System.in);
		loginName = scanner.next();
		System.out.print("密码:");
		password = scanner.next();
		
		UserDAO dao = new UserDAO();
		User user = dao.findUser(loginName, password);
		int choice = 0;
		if (user != null) {  //登录成功 
			while (true) {
//				showMainMenu();
				System.out.print("登录成功!欢迎您，来自" + user.getDeptName() + "的" +
						user.getUserName());
				// 根据用户角色不同，显示不同的菜单
				user.setRoles(dao.getRoles(user.getUserId()));
				if (user.isMemberOfRole(10)) { // 管理员
					System.out.println("同志！");
					doAdmin();
				} else if (user.isMemberOfRole(20)) { // 教师
					System.out.println("老师！");
					doTeacher();
				} else if (user.isMemberOfRole(30)) { // 学生
					System.out.println("同学！");
					showStudentMenu();
				} else {
					System.out.println("朋友！");
					showStudentMenu();
				}
				choice = scanner.nextInt();
				UserDAO udao = new UserDAO();
				switch(choice)
				{
					case 11:
						System.out.println("增加学生信息");
						User u = DataInput.inputUser();
						if (udao.insert(u, String.valueOf(u.getRoles().get(0)))) {
							System.out.println("新增用户成功!");
						} else {
							System.out.println("新增用户失败!");
						}
						break;
					case 12:
						System.out.println("更改学生信息");
						break;
					case 13:
						System.out.println("删除学生信息");
						break;
					case 14:
						System.out.println("列出学生信息");
						break;
					case 15:
						System.out.println("查询学生信息");
//						**JOptionPane中输入对话框
//						JOptionPane.showInputDialog(jFrame, "请输入你的银行卡账号", "输入对话框",JOptionPane.INFORMATION_MESSAGE);//父窗口，提示名，标题，对话框类型
						String sname = JOptionPane.showInputDialog(null, "请输入用户名：");
						List<User> users = udao.queryByName(sname);
						System.out.println("找到" + users.size() + "个用户。");
//						**这个格式很nice 之后可以用
						System.out.println("帐户名\t\t姓名\t性别\t入学年份\t班级\t手机号");
						System.out.println("---------------------------------------");
						for (User uu : users) {
							System.out.print(uu.getLoginName() + "\t");
							System.out.print(uu.getUserName() + "\t");
							if (uu.getSex() == '0')
								System.out.print("女\t");
							else
								System.out.print("男\t");
							System.out.print(uu.getEntranceYear() + "\t");
							System.out.println(uu.getDeptName() + "\t" + uu.getCell());
						}
						break;
					case 16:
						System.out.println("更改用户");
						String strID = JOptionPane.showInputDialog(null, "请输入待修改的用户ID：");
						try {
							int uId = Integer.parseInt(strID);
							u = DataInput.editUser(uId);
							if (udao.update(u)) {
								JOptionPane.showMessageDialog(null, "用户更新成功!");;
							}
						} catch (Exception e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "用户修改失败。");
						}
						break;
					case 17:
						strID = JOptionPane.showInputDialog(null, "请输入待删除的用户ID：");
						try {
							int uId = Integer.parseInt(strID);
							u = udao.getUserById(uId);
							int yesNo = JOptionPane.showConfirmDialog(null, "真的要删除用户“" +
									u.getUserName() + "”吗?");
							if (yesNo != JOptionPane.YES_OPTION) break;
							if (udao.delete(uId)) {
								JOptionPane.showMessageDialog(null, "用户删除成功!");;
							}
						} catch (Exception e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "用户删除失败!");
						}
						break;
					case 21:
						System.out.println("增加课程信息");
						break;
					case 22:
						System.out.println("更改课程信息");
						break;
					case 23:
						System.out.println("删除课程信息");
						break;
					case 24:
						System.out.println("列出课程信息");
						break;
					case 0:
						System.out.println("Bye!");
						break;
				}
				System.out.println("\n");
				if (choice == 0)
					break;
			}
		}
		else
		{
			Util.Log("用户名或密码有误!");
		}
		
		// Do some cleaning work
		if(choice == 0)
		{
			scanner.close();
		}
		
	}

	private static void showStudentMenu() {
		System.out.println("[1] 学籍管理");
		System.out.println("     [14] 列出学生信息");
		System.out.println("[2] 成绩管理");
		System.out.println("     [24] 列出课程信息");
		System.out.println("[0] 退出系统");
	}

	private static void doTeacher() {
		System.out.println("[1] 学籍管理");
		System.out.println("     [11] 增加学生信息");
		System.out.println("     [12] 更改学生信息");
		System.out.println("     [13] 删除学生信息");
		System.out.println("     [14] 列出学生信息");
		System.out.println("[2] 成绩管理");
		System.out.println("     [21] 增加课程信息");
		System.out.println("     [22] 更改课程信息");
		System.out.println("     [23] 删除课程信息");
		System.out.println("     [24] 列出课程信息");
		//System.out.println("     [25] 输入学生成绩");
		System.out.println("[0] 退出系统");
	}

	private static void doAdmin() {
		System.out.println("[1] 学籍管理");
		System.out.println("     [11] 增加学生信息");
		System.out.println("     [12] 更改学生信息");
		System.out.println("     [13] 删除学生信息");
		System.out.println("     [14] 列出学生信息");
		System.out.println("     [15] 查询学生信息");
		System.out.println("[2] 成绩管理");
		System.out.println("     [21] 增加课程信息");
		System.out.println("     [22] 更改课程信息");
		System.out.println("     [23] 删除课程信息");
		System.out.println("     [24] 列出课程信息");
		//System.out.println("     [25] 输入学生成绩");
		System.out.println("[0] 退出系统");
	}

	/**
	 *  显示主界面菜单
	 */
	private static void showMainMenu()
	{
		System.out.println("[1] 学籍管理");
		System.out.println("     [11] 增加学生信息");
		System.out.println("     [12] 更改学生信息");
		System.out.println("     [13] 删除学生信息");
		System.out.println("     [14] 列出学生信息");
		System.out.println("[2] 成绩管理");
		System.out.println("     [21] 增加课程信息");
		System.out.println("     [22] 更改课程信息");
		System.out.println("     [23] 删除课程信息");
		System.out.println("     [24] 列出课程信息");
		//System.out.println("     [25] 输入学生成绩");
		System.out.println("[0] 退出系统");
		
	}

}
