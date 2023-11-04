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
            System.out.println("------�������û���ϸ��Ϣ------");
            while (true) {
                System.out.print("��¼�ʻ���");
                String loginName = in.nextLine(); user.setLoginName(loginName);
                // ������˻����Ƿ��Ѿ�����
                User u = new UserDAO().findUser(loginName, null);
                if (u != null) {
                    Util.Log("�ʻ����Ѿ�����");
                    continue;
                } else {
                    break;
                }
            }
            System.out.print("��ʵ������");
            String userName = in.nextLine(); user.setUserName(userName);
            System.out.print("���룺");
            String userPwd = in.nextLine(); user.setPassword(userPwd);
            System.out.print("ѧ��(����)��");
            String userCode = in.nextLine(); user.setUserCode(userCode);
            System.out.print("�Ա�(0-Ů��1-��)��");
            String sex = in.nextLine(); user.setSex(sex.trim().charAt(0));
            System.out.print("Email��");
            String email = in.nextLine(); user.setEmail(email);
            System.out.print("��ĸEmail��");
            String pemail = in.nextLine(); user.setParentEmail(pemail);
            System.out.print("�ֻ���");
            String cell = in.nextLine(); user.setCell(cell);
//            System.out.print("�༶����(4-08�ż�1��; 5-07�ż�1��; 6-09�ż�1��)��");
//            String deptId = in.nextLine(); user.setDeptId(deptId);
            System.out.println("�༶���ű��(4-08�ż�1��; 5-07�ż�1��; 6-09�ż�1��)��");
            DeptDAO deptDao = new DeptDAO();
            List<Department> departmentList = deptDao.getAllDepts();
            System.out.println("ID\t Name \t\t");
            for (Department d : departmentList) {
                System.out.println(d.getId() + "\t" + d.getName());
            }
            System.out.print("��ѡ��һ�����ŵ�ID��");
            String deptId = in.nextLine(); user.setDeptId(deptId);
            System.out.print("��ѧ��ݣ�");
            String enYear = in.nextLine(); user.setEntranceYear(enYear);
            System.out.print("���ҽ��ܣ�");
            String introduce = in.nextLine(); user.setIntroduce(introduce);
            System.out.print("��ɫ(10-ѧ����20-��ʦ��30-����Ա)��");
            String role = in.nextLine();
            user.getRoles().add(Integer.parseInt(role));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    /**
     * ʵ���û����ݵı༭����Ҫ���ֳ����е��������ݣ����û��༭
     * @param uId
     * @return
     */
    public static User editUser(int uId) {
        UserDAO dao = new UserDAO();
        User user = dao.getUserById(uId);
        String userName = (String) JOptionPane.showInputDialog(null, "��ʵ������",
                "�û��޸�", JOptionPane.QUESTION_MESSAGE, null, null, user.getUserName());
        user.setUserName(userName);
        String userPwd = (String)JOptionPane.showInputDialog(null, "���룺", "�û��޸�", JOptionPane.QUESTION_MESSAGE, null, null, user.getPassword());
                user.setPassword(userPwd);
        String userCode = (String)JOptionPane.showInputDialog(null, "ѧ��(����)��", "�û��޸�", JOptionPane.QUESTION_MESSAGE, null, null, user.getUserCode());
                user.setUserCode(userCode);
        String[] ss = {"��", "Ů"};
        String selSex = (user.getSex() == '1') ? "��" : "Ů";
        String sex = (String)JOptionPane.showInputDialog(null, "�Ա�(0-Ů��1-��)��", "�û��޸�", JOptionPane.QUESTION_MESSAGE, null, ss, selSex);
        char csex = sex.equals("��") ? '1' : '0';
        user.setSex(csex);
        String email = (String)JOptionPane.showInputDialog(null, "Email��", "�û��޸�", JOptionPane.QUESTION_MESSAGE, null, null, user.getEmail());
                user.setEmail(email);
        String pemail = (String)JOptionPane.showInputDialog(null, "��ĸEmail��", "�û��޸�", JOptionPane.QUESTION_MESSAGE, null, null, user.getParentEmail());
                user.setParentEmail(pemail);
        String cell = (String)JOptionPane.showInputDialog(null, "�ֻ���", "�û��޸�", JOptionPane.QUESTION_MESSAGE, null, null, user.getCell());
                user.setCell(cell);
/////�༶ѡ��Ӧ�ô����ݿ��ȡ���ݹ��û�ѡ������������д��/////
        String[] ds = {"4-08�ż�1��", "5-07�ż�1��", "6-09�ż�1��"};
        String deptId = (String)JOptionPane.showInputDialog(null, "�༶���ű�ţ�", "�û��޸�", JOptionPane.QUESTION_MESSAGE, null, ds, user.getDeptId());
                user.setDeptId(deptId.substring(0, deptId.indexOf('-')));
        String enYear = (String)JOptionPane.showInputDialog(null, "��ѧ��ݣ�", "�û��޸�", JOptionPane.QUESTION_MESSAGE, null, null, user.getEntranceYear());
                user.setEntranceYear(enYear);
        String introduce = (String)JOptionPane.showInputDialog(null, "���ҽ��ܣ�", "�û��޸�", JOptionPane.QUESTION_MESSAGE, null, null, user.getIntroduce());
                user.setIntroduce(introduce);
        String[] rs = {"10-ѧ��", "20-��ʦ", "30-����Ա"};
        List<Integer> roles = dao.getRoles(uId);
        String role = (String)JOptionPane.showInputDialog(null, "��ɫ��", "�û��޸�", JOptionPane.QUESTION_MESSAGE, null, rs, roles.get(0));
                user.getRoles().add(Integer.parseInt(role.substring(0, 2)));
        return user;
    }
}