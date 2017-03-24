package ac.kr.jejunu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by adaeng on 2017. 3. 24..
 */
public class jejuConnetionMaker implements ConntionMaker {

    @Override
    public Connection getconnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/test?charasetEncoding=utf-8", "root", "as0109247");
        return connection;
    }
}
