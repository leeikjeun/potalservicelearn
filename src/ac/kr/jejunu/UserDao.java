package ac.kr.jejunu;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by adaeng on 2017. 3. 15..
 */
public class UserDao {

    private JdbcContext jdbcContext;

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Long id) throws ClassNotFoundException, SQLException {
        String sql = "select * from user where id = ?";
        Object[] parms = new Object[] {id};
        return jdbcContext.queryForObject(sql, parms);
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        String sql = "INSERT into user(name,password) VALUES (?,?)";
        Object[] parms = new Object[] {user.getName(), user.getPassword()};
        return jdbcContext.insert(sql, parms);
    }

    public void update(User user) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE user set name = ?, password = ? where id = ?";
        Object[] parms = new Object[] {user.getName() ,user.getPassword() ,user.getId()};
        jdbcContext.update(sql, parms);
    }

    public void delete(Long id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE from user where id = ?";
        Object[] parms = new Object[] {id};
        jdbcContext.update(sql, parms);
    }


}
