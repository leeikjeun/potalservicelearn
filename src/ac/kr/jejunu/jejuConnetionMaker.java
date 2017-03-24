package ac.kr.jejunu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by adaeng on 2017. 3. 24..
 */
public class jejuConnetionMaker implements ConntionMaker {
    private String id;
    private String password;
    private String url;
    private String classname;

    @Override
    public Connection getconnection() throws ClassNotFoundException, SQLException {
        Class.forName(classname);
        Connection connection = DriverManager.getConnection(url, id ,password);
        return connection;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
