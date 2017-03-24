package ac.kr.jejunu;

/**
 * Created by adaeng on 2017. 3. 24..
 */
public class DaoFactory {

    

    public UserDao getUserDao() {
        return new UserDao(new jejuConnetionMaker());
    }

}
