package cn.net.nit.ems;

import java.util.List;
import java.util.Scanner;

/**
 * @author lyq
 * @time 2023/11/4 21:00
 */
public class DataInput {
    public static User inputUser() {
        User user = new User();
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("------请输入用户详细信息------");
            while (true) {
                System.out.print("登录帐户：");
                String loginName = in.nextLine(); user.setLoginName(loginName);
                // 检验该账户名是否已经存在
                User u = new UserDAO().findUser(loginName, null);
                if (u != null) {
                    Util.Log("帐户名已经存在");
                    continue;
                } else {
                    break;
                }
            }
            System.out.print("真实姓名：");
            String userName = in.nextLine(); user.setUserName(userName);
            System.out.print("密码：");
            String userPwd = in.nextLine(); user.setPassword(userPwd);
            System.out.print("学号(工号)：");
            String userCode = in.nextLine(); user.setUserCode(userCode);
            System.out.print("性别(0-女；1-男)：");
            String sex = in.nextLine(); user.setSex(sex.trim().charAt(0));
            System.out.print("Email：");
            String email = in.nextLine(); user.setEmail(email);
            System.out.print("父母Email：");
            String pemail = in.nextLine(); user.setParentEmail(pemail);
            System.out.print("手机：");
            String cell = in.nextLine(); user.setCell(cell);
//            System.out.print("班级或部门(4-08信计1班; 5-07信计1班; 6-09信计1班)：");
//            String deptId = in.nextLine(); user.setDeptId(deptId);
            System.out.println("班级或部门编号(4-08信计1班; 5-07信计1班; 6-09信计1班)：");
            DeptDAO deptDao = new DeptDAO();
            List<Department> departmentList = deptDao.getAllDepts();
            System.out.println("ID\t Name \t\t");
            for (Department d : departmentList) {
                System.out.println(d.getId() + "\t" + d.getName());
            }
            System.out.print("请选择一个部门的ID：");
            String deptId = in.nextLine(); user.setDeptId(deptId);
            System.out.print("入学年份：");
            String enYear = in.nextLine(); user.setEntranceYear(enYear);
            System.out.print("自我介绍：");
            String introduce = in.nextLine(); user.setIntroduce(introduce);
            System.out.print("角色(10-学生；20-教师；30-管理员)：");
            String role = in.nextLine();
            user.getRoles().add(Integer.parseInt(role));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }
}