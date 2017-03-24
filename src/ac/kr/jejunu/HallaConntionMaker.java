package ac.kr.jejunu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by adaeng on 2017. 3. 24..
 */
public class HallaConntionMaker implements ConntionMaker {
    @Override
    public Connection getconnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/test?charasetEncoding=utf-8", "root", "as0109247");
        return connection;
    }

    @Override
    public void setId(String id) {

    }

    @Override
    public void setPassword(String password) {

    }

    @Override
    public void setUrl(String url) {

    }

    @Override
    public void setClassname(String classname) {

    }
}
