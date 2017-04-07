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
        StatmentStrategy statmentStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user where id = ?");
            preparedStatement.setLong(1, id);
            return preparedStatement;
        };
        return jdbcContext.jdbcContextWithStatementStrategyForGet(statmentStrategy);
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        StatmentStrategy statmentStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into user(name,password) VALUES (?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            return preparedStatement;
        };

        return jdbcContext.jdbcContextWithStatementStrategyForInsert(statmentStrategy);
    }

    public void update(User user) throws SQLException, ClassNotFoundException {
        StatmentStrategy statmentStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user set name = ?, password = ? where id = ?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getId());
            return preparedStatement;
        };

        jdbcContext.jdbcContextWithStatementStrategyForUpdate(statmentStrategy);
    }

    public void delete(Long id) throws SQLException, ClassNotFoundException {
        StatmentStrategy statmentStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE from user where id = ?");
            preparedStatement.setLong(1, id);
            return preparedStatement;
        };
        jdbcContext.jdbcContextWithStatementStrategyForUpdate(statmentStrategy);
    }
}
