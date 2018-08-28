package redis;

import com.wck.WckApplication;
import com.wck.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WckApplication.class)
public class TestRedis {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void testObj() throws Exception {
        User user = new User("aaa@126.com", "aa123456", "aa", "aa", "123");
        ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("com.nexo", user);
        valueOperations.set("com.nexf", user, 1, TimeUnit.SECONDS);

        Thread.sleep(1000);

        Boolean hasKey = redisTemplate.hasKey("com.nexf");
        if (hasKey) {
            System.out.println("exists is true");
        } else {
            System.out.println("exists is false");
        }
    }
}
