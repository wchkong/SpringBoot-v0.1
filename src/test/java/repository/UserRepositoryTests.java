package repository;

import com.wck.WckApplication;
import com.wck.domain.User;
import com.wck.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WckApplication.class)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() throws Exception {
        Date date = new Date();

        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String format = dateFormat.format(date);

        userRepository.save(new User("aa1", "aa@126.com", "aa", "aa123456", format));
        userRepository.save(new User("bb1", "bb@126.com", "bb", "bb123456", format));
        userRepository.save(new User("cc3", "cc@126.com", "cc", "cc123456" , format));

        Assert.assertEquals(9, userRepository.findAll().size());
        Assert.assertEquals("bb", userRepository.findByUserNameOrEmail("bb", "cc@126.com").getNickName());
        userRepository.delete(userRepository.findByUserName("aa1"));
    }

}
