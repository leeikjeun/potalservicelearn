package ac.kr.jejunu;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;

/**
 * Created by adaeng on 2017. 3. 15..
 */
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public User get(Long id) throws ClassNotFoundException, SQLException {
        String sql = "select * from user where id = ?";
        Object[] parms = new Object[] {id};
        User user1 =null;
        try {
            user1 = jdbcTemplate.queryForObject(sql,parms,(resultSet, i) -> {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                return user;
            });
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return user1;
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        String sql = "INSERT into user(name,password) VALUES (?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Object[] parms = new Object[] {user.getName(), user.getPassword()};
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            return preparedStatement;
        }, keyHolder);

        return (Long) keyHolder.getKey();
    }

    public void update(User user) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE user set name = ?, password = ? where id = ?";
        Object[] parms = new Object[] {user.getName() ,user.getPassword() ,user.getId()};
        jdbcTemplate.update(sql, parms);
    }

    public void delete(Long id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE from user where id = ?";
        Object[] parms = new Object[] {id};
        jdbcTemplate.update(sql, parms);
    }


    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
