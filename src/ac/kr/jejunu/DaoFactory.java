package ac.kr.jejunu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by adaeng on 2017. 3. 24..
 */

@Configuration
public class DaoFactory {

//    @Bean
//    public UserDao UserDao() {
//        return new UserDao(getConnationMaker());
//    }
    /*
    *  UserDao userDao = new UserDao();
    * userDao.setConnectionMaker(ConnetionMaker());
    * return userDao;
    *
    * 이방법은 xml사용하지 않고 userDao 생성자 말고 set을 사용했을시!!
    * */


    @Bean
    public ConntionMaker getConnationMaker(){
        return new jejuConnetionMaker();
    }
}
