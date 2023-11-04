package cn.net.nit.ems;

import cn.net.nit.ems.dao.UserDAO;
import cn.net.nit.ems.domain.User;
import cn.net.nit.ems.util.DataInput;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;

/**
 * ��ѧ����ϵͳ����
 * @author Rainkee Liu
 *
 */
public class EMSApp {

	public static void main(String[] args) {
		
		System.out.println("*******************************************");
		System.out.println("***                                     ***");
		System.out.println("***          �������ϵͳ   v0.1           ***");
		System.out.println("***                                     ***");
		System.out.println("*******************************************");
		String loginName, password;
		System.out.print("�û���:");
		Scanner scanner = new Scanner(System.in);
		loginName = scanner.next();
		System.out.print("����:");
		password = scanner.next();
		
		UserDAO dao = new UserDAO();
		User user = dao.findUser(loginName, password);
		int choice = 0;
		if (user != null) {  //��¼�ɹ� 
			while (true) {
//				showMainMenu();
				System.out.print("��¼�ɹ�!��ӭ��������" + user.getDeptName() + "��" +
						user.getUserName());
				// �����û���ɫ��ͬ����ʾ��ͬ�Ĳ˵�
				user.setRoles(dao.getRoles(user.getUserId()));
				if (user.isMemberOfRole(10)) { // ����Ա
					System.out.println("ͬ־��");
					doAdmin();
				} else if (user.isMemberOfRole(20)) { // ��ʦ
					System.out.println("��ʦ��");
					doTeacher();
				} else if (user.isMemberOfRole(30)) { // ѧ��
					System.out.println("ͬѧ��");
					showStudentMenu();
				} else {
					System.out.println("���ѣ�");
					showStudentMenu();
				}
				choice = scanner.nextInt();
				UserDAO udao = new UserDAO();
				switch(choice)
				{
					case 11:
						System.out.println("����ѧ����Ϣ");
						User u = DataInput.inputUser();
						if (udao.insert(u, String.valueOf(u.getRoles().get(0)))) {
							System.out.println("�����û��ɹ�!");
						} else {
							System.out.println("�����û�ʧ��!");
						}
						break;
					case 12:
						System.out.println("����ѧ����Ϣ");
						break;
					case 13:
						System.out.println("ɾ��ѧ����Ϣ");
						break;
					case 14:
						System.out.println("�г�ѧ����Ϣ");
						break;
					case 15:
						System.out.println("��ѯѧ����Ϣ");
//						**JOptionPane������Ի���
//						JOptionPane.showInputDialog(jFrame, "������������п��˺�", "����Ի���",JOptionPane.INFORMATION_MESSAGE);//�����ڣ���ʾ�������⣬�Ի�������
						String sname = JOptionPane.showInputDialog(null, "�������û�����");
						List<User> users = udao.queryByName(sname);
						System.out.println("�ҵ�" + users.size() + "���û���");
//						**�����ʽ��nice ֮�������
						System.out.println("�ʻ���\t\t����\t�Ա�\t��ѧ���\t�༶\t�ֻ���");
						System.out.println("---------------------------------------");
						for (User uu : users) {
							System.out.print(uu.getLoginName() + "\t");
							System.out.print(uu.getUserName() + "\t");
							if (uu.getSex() == '0')
								System.out.print("Ů\t");
							else
								System.out.print("��\t");
							System.out.print(uu.getEntranceYear() + "\t");
							System.out.println(uu.getDeptName() + "\t" + uu.getCell());
						}
						break;
					case 16:
						System.out.println("�����û�");
						String strID = JOptionPane.showInputDialog(null, "��������޸ĵ��û�ID��");
						try {
							int uId = Integer.parseInt(strID);
							u = DataInput.editUser(uId);
							if (udao.update(u)) {
								JOptionPane.showMessageDialog(null, "�û����³ɹ�!");;
							}
						} catch (Exception e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "�û��޸�ʧ�ܡ�");
						}
						break;
					case 17:
						strID = JOptionPane.showInputDialog(null, "�������ɾ�����û�ID��");
						try {
							int uId = Integer.parseInt(strID);
							u = udao.getUserById(uId);
							int yesNo = JOptionPane.showConfirmDialog(null, "���Ҫɾ���û���" +
									u.getUserName() + "����?");
							if (yesNo != JOptionPane.YES_OPTION) break;
							if (udao.delete(uId)) {
								JOptionPane.showMessageDialog(null, "�û�ɾ���ɹ�!");;
							}
						} catch (Exception e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "�û�ɾ��ʧ��!");
						}
						break;
					case 21:
						System.out.println("���ӿγ���Ϣ");
						break;
					case 22:
						System.out.println("���Ŀγ���Ϣ");
						break;
					case 23:
						System.out.println("ɾ���γ���Ϣ");
						break;
					case 24:
						System.out.println("�г��γ���Ϣ");
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
			Util.Log("�û�������������!");
		}
		
		// Do some cleaning work
		if(choice == 0)
		{
			scanner.close();
		}
		
	}

	private static void showStudentMenu() {
		System.out.println("[1] ѧ������");
		System.out.println("     [14] �г�ѧ����Ϣ");
		System.out.println("[2] �ɼ�����");
		System.out.println("     [24] �г��γ���Ϣ");
		System.out.println("[0] �˳�ϵͳ");
	}

	private static void doTeacher() {
		System.out.println("[1] ѧ������");
		System.out.println("     [11] ����ѧ����Ϣ");
		System.out.println("     [12] ����ѧ����Ϣ");
		System.out.println("     [13] ɾ��ѧ����Ϣ");
		System.out.println("     [14] �г�ѧ����Ϣ");
		System.out.println("[2] �ɼ�����");
		System.out.println("     [21] ���ӿγ���Ϣ");
		System.out.println("     [22] ���Ŀγ���Ϣ");
		System.out.println("     [23] ɾ���γ���Ϣ");
		System.out.println("     [24] �г��γ���Ϣ");
		//System.out.println("     [25] ����ѧ���ɼ�");
		System.out.println("[0] �˳�ϵͳ");
	}

	private static void doAdmin() {
		System.out.println("[1] ѧ������");
		System.out.println("     [11] ����ѧ����Ϣ");
		System.out.println("     [12] ����ѧ����Ϣ");
		System.out.println("     [13] ɾ��ѧ����Ϣ");
		System.out.println("     [14] �г�ѧ����Ϣ");
		System.out.println("     [15] ��ѯѧ����Ϣ");
		System.out.println("[2] �ɼ�����");
		System.out.println("     [21] ���ӿγ���Ϣ");
		System.out.println("     [22] ���Ŀγ���Ϣ");
		System.out.println("     [23] ɾ���γ���Ϣ");
		System.out.println("     [24] �г��γ���Ϣ");
		//System.out.println("     [25] ����ѧ���ɼ�");
		System.out.println("[0] �˳�ϵͳ");
	}

	/**
	 *  ��ʾ������˵�
	 */
	private static void showMainMenu()
	{
		System.out.println("[1] ѧ������");
		System.out.println("     [11] ����ѧ����Ϣ");
		System.out.println("     [12] ����ѧ����Ϣ");
		System.out.println("     [13] ɾ��ѧ����Ϣ");
		System.out.println("     [14] �г�ѧ����Ϣ");
		System.out.println("[2] �ɼ�����");
		System.out.println("     [21] ���ӿγ���Ϣ");
		System.out.println("     [22] ���Ŀγ���Ϣ");
		System.out.println("     [23] ɾ���γ���Ϣ");
		System.out.println("     [24] �г��γ���Ϣ");
		//System.out.println("     [25] ����ѧ���ɼ�");
		System.out.println("[0] �˳�ϵͳ");
		
	}

}
