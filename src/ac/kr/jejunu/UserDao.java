package ac.kr.jejunu;

import java.sql.*;

/**
 * Created by adaeng on 2017. 3. 15..
 */
public class UserDao {
    private ConntionMaker conntionMaker;

    public UserDao(ConntionMaker conntionMaker){
        this.conntionMaker = conntionMaker;
    }

    public User get(Long id) throws ClassNotFoundException, SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = conntionMaker.getconnection();
            //쿼리를만들어야겠네
            StatmentStrategy statmentStrategy = new GetUserStatementStrategy();
            preparedStatement =statmentStrategy.makeStatement(id,connection);
            //쿼리를실행해야겠네
            resultSet = preparedStatement.executeQuery();
            // 실행된 결과를 객체에 매핑해야겠네
            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
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
        // 결과를 리턴해야겠네
        //자원해제를 왜 따로 하냐면 커넥션이 다른곳에서 사용할수 있기 때문에 작은 단위부터
        return user;
    }


    public Long add(User user) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long id = null;

        try {
            connection = conntionMaker.getconnection();
            StatmentStrategy statmentStrategy = new AddUserStatementStrategy();
            preparedStatement =statmentStrategy.makeStatement(user,connection);
            //쿼리를실행해야겠네
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("select last_INSERT_id()");
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getLong(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
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

        return id;
    }

    private Connection getconnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://113.198.162.186/test?charasetEncoding=utf-8", "root", "as0109247");
        return connection;
    }

    public void update(User user) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = conntionMaker.getconnection();
            StatmentStrategy statmentStrategy = new UpdateUserStatementStrategy();
            preparedStatement =statmentStrategy.makeStatement(user,connection);
            //쿼리를실행해야겠네
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
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

    public void delete(Long id) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = conntionMaker.getconnection();
            StatmentStrategy statmentStrategy = new DeleteUserStatementStrategy();
            preparedStatement =statmentStrategy.makeStatement(id,connection);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
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
}
