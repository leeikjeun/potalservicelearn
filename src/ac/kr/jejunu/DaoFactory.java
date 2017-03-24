package ac.kr.jejunu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by adaeng on 2017. 3. 24..
 */

@Configuration
public class DaoFactory {

    @Bean
    public UserDao UserDao() {
        return new UserDao(getConnationMaker());
    }

    @Bean
    public ConntionMaker getConnationMaker(){
        return new jejuConnetionMaker();
    }
}
