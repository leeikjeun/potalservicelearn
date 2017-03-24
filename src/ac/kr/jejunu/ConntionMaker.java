package ac.kr.jejunu;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by adaeng on 2017. 3. 24..
 */
public interface ConntionMaker {
    public Connection getconnection() throws ClassNotFoundException, SQLException;
}
