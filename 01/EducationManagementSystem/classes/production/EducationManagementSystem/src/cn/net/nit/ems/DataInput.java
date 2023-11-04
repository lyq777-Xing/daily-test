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
}