import cn.net.nit.ems.User;
import cn.net.nit.ems.UserDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class UserDAOTest {
    @Test
    public void test1() {
        User user = new User();
        user.setLoginName("lyq");
        user.setUserName("lyq");
        user.setPassword("123456");
        user.setUserCode("9999");
        user.setSex('1');
        user.setEmail("rainkee@gmail.com");
        user.setDeptId("3");
        UserDAO dao = new UserDAO();
        dao.insert(user, "30");
    }
}