package ac.kr.jejunu;

/**
 * Created by adaeng on 2017. 3. 15..
 */

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.Connection;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;

public class UserDaoTest {
    UserDao userDao;

    @Before
    public void setup(){
        //daoFactory = new DaoFactory();
        //ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        ApplicationContext context = new GenericXmlApplicationContext("daoFactory.xml");
        userDao = context.getBean("userDao",UserDao.class);
    }
    //before을 하면 테스트 케이스 하기전에 먼저 돌아감

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 10L;
        String name = "이익전";
        String password = "1234";

        //UserDao userDao = daoFactory.UserDao();
        User user = userDao.get(id);
        assertThat(id, is(user.getId())); //테스트 코드 id 랑 뒤에 is 랑 비교 테스트 코드에서만 사용
        assertThat(name, is(user.getName()));
        assertThat(password, is(user.getPassword()));
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        //void로 충분히 괜찮은 애이나 테스트를 위해 리턴을 해줌
        String name = "lllqwe";
        String password = "1234";
        User user = new User();

        user.setName(name);
        user.setPassword(password);
        //UserDao userDao = daoFactory.UserDao();
        Long id = userDao.add(user);
        User resultUser = userDao.get(id);
        assertThat(id, is(resultUser.getId()));
        assertThat(name, is(resultUser.getName()));
        assertThat(password, is(resultUser.getPassword()));
    }

    @Test
    public void update() throws SQLException, ClassNotFoundException {
        String name = "lllqwe";
        String password = "1234";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Long id = userDao.add(user);
        //UserDao userDao = daoFactory.UserDao();

        String chageName = "testname";
        String chagepass = "testpass";

        user.setId(id);
        user.setName(chageName);
        user.setPassword(chagepass);

        userDao.update(user);

        User changeUser = userDao.get(id);

        assertThat(id, is(changeUser.getId()));
        assertThat(chageName, is(changeUser.getName()));
        assertThat(chagepass, is(changeUser.getPassword()));
    }

    @Test
    public void delate() throws SQLException, ClassNotFoundException {
        String name = "lllqwe";
        String password = "1234";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Long id = userDao.add(user);

        userDao.delete(id);

        User deleteUser = userDao.get(id);

        assertThat(deleteUser, nullValue());
    }

    @Test
    public void getHalla() throws SQLException, ClassNotFoundException {
        Long id = 10L;
        String name = "이익전";
        String password = "1234";

        UserDao userDao = new UserDao(new HallaConntionMaker());
        User user = userDao.get(id);
        assertThat(id, is(user.getId())); //테스트 코드 id 랑 뒤에 is 랑 비교 테스트 코드에서만 사용
        assertThat(name, is(user.getName()));
        assertThat(password, is(user.getPassword()));
    }

    @Test
    public void addHalla() throws SQLException, ClassNotFoundException {
        //void로 충분히 괜찮은 애이나 테스트를 위해 리턴을 해줌
        String name = "leeikjeon";
        String password = "1234";
        User user = new User();

        user.setName(name);
        user.setPassword(password);
        UserDao userDao = new UserDao(new HallaConntionMaker());
        Long id = userDao.add(user);
        User resultUser = userDao.get(id);
        assertThat(id, is(resultUser.getId()));
        assertThat(name, is(resultUser.getName()));
        assertThat(password, is(resultUser.getPassword()));
    }
}
