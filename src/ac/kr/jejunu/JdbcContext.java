package ac.kr.jejunu;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by adaeng on 2017. 4. 7..
 */
public class JdbcContext {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User jdbcContextWithStatementStrategyForGet(StatmentStrategy statmentStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = dataSource.getConnection();
            //쿼리를만들어야겠네
            preparedStatement =statmentStrategy.makeStatement(connection);
            //쿼리를실행해야겠네
            resultSet = preparedStatement.executeQuery();
            // 실행된 결과를 객체에 매핑해야겠네
            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return user;
    }

    public Long jdbcContextWithStatementStrategyForInsert(StatmentStrategy statmentStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long id = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement =statmentStrategy.makeStatement(connection);
            //쿼리를실행해야겠네
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("select last_INSERT_id()");
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getLong(1);
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return id;
    }

    public Connection getconnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://113.198.162.186/test?charasetEncoding=utf-8", "root", "as0109247");
        return connection;
    }

    public void jdbcContextWithStatementStrategyForUpdate(StatmentStrategy statmentStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement =statmentStrategy.makeStatement(connection);
            //쿼리를실행해야겠네
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    public Long insert(String sql, Object[] parms) throws SQLException {
        StatmentStrategy statmentStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for(int i = 1 ; i <= parms.length; i++){
                preparedStatement.setObject(i, parms[i - 1]);
            }
            return preparedStatement;
        };

        return jdbcContextWithStatementStrategyForInsert(statmentStrategy);
    }

    public User queryForObject(String sql, Object[] parms) throws SQLException {
        StatmentStrategy statmentStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for(int i = 1 ; i <=parms.length; i++){
                preparedStatement.setObject(i, parms[i - 1]);
            }
            return preparedStatement;
        };

        return jdbcContextWithStatementStrategyForGet(statmentStrategy);
    }

    public void update(String sql, Object[] parms) throws SQLException {
        StatmentStrategy statmentStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for(int i = 1 ; i <= parms.length; i++){
                preparedStatement.setObject(i, parms[i - 1]);
            }
            return preparedStatement;
        };

        jdbcContextWithStatementStrategyForUpdate(statmentStrategy);
    }
}
