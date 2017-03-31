package ac.kr.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by adaeng on 2017. 3. 31..
 */
public class AddUserStatementStrategy implements StatmentStrategy {
    @Override
    public PreparedStatement makeStatement(Object object, Connection connection) throws SQLException {
        User user = (User) object;
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT into user(name,password) VALUES (?,?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        return preparedStatement;
    }
}
