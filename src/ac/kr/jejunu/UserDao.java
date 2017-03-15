package ac.kr.jejunu;

import java.sql.*;

/**
 * Created by adaeng on 2017. 3. 15..
 */
public class UserDao {
    public User get(Long id) throws ClassNotFoundException, SQLException {
        //User 어디에있어? Mysql
        //Class 를 로딩해야되겠네.
        Class.forName("com.mysql.jdbc.Driver");
        //커넥션을 맺기
        Connection connection = DriverManager.getConnection("jdbc:mysql://113.198.162.186/test", "root", "as0109247");
        //쿼리를만들어야겠네
        PreparedStatement preparedStatement = connection.prepareStatement("select * from user where id = ?");
        preparedStatement.setLong(1, id);
        //쿼리를실행해야겠네
        ResultSet resultSet = preparedStatement.executeQuery();
        // 실행된 결과를 객체에 매핑해야겠네
        resultSet.next();
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        // 자원들을 해제하고
        resultSet.close();
        preparedStatement.close();
        connection.close();
        // 결과를 리턴해야겠네
        //자원해제를 왜 따로 하냐면 커넥션이 다른곳에서 사용할수 있기 때문에 작은 단위부터
        return user;
    }


    public Long add(User user) throws ClassNotFoundException, SQLException {
        Connection connection = getconnection();
        //쿼리를만들어야겠네
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT into user(name,password) VALUES (?,?)");

        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        //쿼리를실행해야겠네
        preparedStatement.executeUpdate();

        preparedStatement = connection.prepareStatement("select last_INSERT_id()");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Long id = resultSet.getLong(1);

        preparedStatement.close();
        connection.close();

        return id;
    }

    private Connection getconnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://113.198.162.186/test?charasetEncoding=utf-8", "root", "as0109247");
        return connection;
    }

}
