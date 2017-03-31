package ac.kr.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by adaeng on 2017. 3. 31..
 */
public class DeleteUserStatementStrategy implements StatmentStrategy {
    @Override
    public PreparedStatement makeStatement(Object object, Connection connection) throws SQLException {
        Long id = (Long) object;
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE from user where id = ?");
        preparedStatement.setLong(1, id);
        return preparedStatement;
    }
}
