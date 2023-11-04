package cn.net.nit.ems.util;

import cn.net.nit.ems.*;
import cn.net.nit.ems.dao.DeptDAO;
import cn.net.nit.ems.dao.UserDAO;
import cn.net.nit.ems.domain.Department;
import cn.net.nit.ems.domain.User;

import javax.swing.*;
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

    /**
     * 实现用户数据的编辑，需要呈现出已有的数据内容，供用户编辑
     * @param uId
     * @return
     */
    public static User editUser(int uId) {
        UserDAO dao = new UserDAO();
        User user = dao.getUserById(uId);
        String userName = (String) JOptionPane.showInputDialog(null, "真实姓名：",
                "用户修改", JOptionPane.QUESTION_MESSAGE, null, null, user.getUserName());
        user.setUserName(userName);
        String userPwd = (String)JOptionPane.showInputDialog(null, "密码：", "用户修改", JOptionPane.QUESTION_MESSAGE, null, null, user.getPassword());
                user.setPassword(userPwd);
        String userCode = (String)JOptionPane.showInputDialog(null, "学号(工号)：", "用户修改", JOptionPane.QUESTION_MESSAGE, null, null, user.getUserCode());
                user.setUserCode(userCode);
        String[] ss = {"男", "女"};
        String selSex = (user.getSex() == '1') ? "男" : "女";
        String sex = (String)JOptionPane.showInputDialog(null, "性别(0-女；1-男)：", "用户修改", JOptionPane.QUESTION_MESSAGE, null, ss, selSex);
        char csex = sex.equals("男") ? '1' : '0';
        user.setSex(csex);
        String email = (String)JOptionPane.showInputDialog(null, "Email：", "用户修改", JOptionPane.QUESTION_MESSAGE, null, null, user.getEmail());
                user.setEmail(email);
        String pemail = (String)JOptionPane.showInputDialog(null, "父母Email：", "用户修改", JOptionPane.QUESTION_MESSAGE, null, null, user.getParentEmail());
                user.setParentEmail(pemail);
        String cell = (String)JOptionPane.showInputDialog(null, "手机：", "用户修改", JOptionPane.QUESTION_MESSAGE, null, null, user.getCell());
                user.setCell(cell);
/////班级选择，应该从数据库读取内容供用户选择，这里简单起见先写死/////
        String[] ds = {"4-08信计1班", "5-07信计1班", "6-09信计1班"};
        String deptId = (String)JOptionPane.showInputDialog(null, "班级或部门编号：", "用户修改", JOptionPane.QUESTION_MESSAGE, null, ds, user.getDeptId());
                user.setDeptId(deptId.substring(0, deptId.indexOf('-')));
        String enYear = (String)JOptionPane.showInputDialog(null, "入学年份：", "用户修改", JOptionPane.QUESTION_MESSAGE, null, null, user.getEntranceYear());
                user.setEntranceYear(enYear);
        String introduce = (String)JOptionPane.showInputDialog(null, "自我介绍：", "用户修改", JOptionPane.QUESTION_MESSAGE, null, null, user.getIntroduce());
                user.setIntroduce(introduce);
        String[] rs = {"10-学生", "20-教师", "30-管理员"};
        List<Integer> roles = dao.getRoles(uId);
        String role = (String)JOptionPane.showInputDialog(null, "角色：", "用户修改", JOptionPane.QUESTION_MESSAGE, null, rs, roles.get(0));
                user.getRoles().add(Integer.parseInt(role.substring(0, 2)));
        return user;
    }
}