package com.example.shiro.redis;

import com.example.shiro.entity.UUser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Description: TODO
 * Package: com.example.shiro.redis
 *
 * @author Leeves
 * @date 2018-03-20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisCache mRedisCache;

    @Test
    public void redisTest() throws Exception {
        //保存字符串
        stringRedisTemplate.opsForValue().set("aaa", "2222288888");
        //读取字符串
        String aaa = stringRedisTemplate.opsForValue().get("aaa");
        System.out.println(aaa);
    }

    @Test
    public void CacheTest() {

        UUser li = mRedisCache.getUerInfo("li");
        System.out.println("first query:" + li);

        mRedisCache.updateUserInfo("li");
  /*      UUser li1 = mRedisCache.getUerInfo1("li");
        System.out.println("send query:"+li1);*/

        UUser li2 = mRedisCache.getUerInfo("li");
        System.out.println("third query:" + li2);
    }

    @Test
    public void test1() {
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        System.out.println("test1:" + opsForValue.get("test1"));
        for (int i = 0; i < 100; i++) {
            opsForValue.increment("test1", 1);
        }
        System.out.println("test1:" + opsForValue.get("test1"));
    }

    @Test
    public void test2() {
        //将日期添加到key值中
        String key = "test2_" + new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        System.out.println("test2:" + opsForValue.get(key));

        for (int i = 0; i < 100; i++) {
            opsForValue.increment(key, 1);
        }
        System.out.println("test2:" + opsForValue.get(key));
    }

    @Test
    public void test3() {
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        //先获取前缀为test的Key值列表。
        Set<String> keys = stringRedisTemplate.keys("test*");
        //遍历满足条件的Key值获取对应的value值
        for (String a : keys) {
            System.out.println(a + ":" + opsForValue.get(a));
        }
    }

    @Test
    public void test4() throws InterruptedException {
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        opsForValue.set("test4", "test4");
        System.out.println("test4:" + opsForValue.get("test4"));
        if (stringRedisTemplate.expire("test4", 10, TimeUnit.SECONDS)) {
            System.out.println("设置过期时间成功,等待。。。。");
            Thread.sleep(5001);
        }

    }

    @Test
    public void test5() {
        UUser user = mRedisCache.getUerInfo("li");
        HashOperations<String, Object, UUser> hash = redisTemplate.opsForHash();
        hash.put("test5", user.getEmail(), user);
        System.out.println("test5:" + hash.get("test5", user.getEmail()));
    }

    @Test
    public void test6() {
        ListOperations<String, String> list = stringRedisTemplate.opsForList();
        list.leftPush("test6", "1");
        list.leftPush("test6", "2");
        list.leftPush("test6", "3");
        list.leftPush("test6", "4");
        list.leftPush("test6", "5");
        list.leftPush("test6", "6");
        list.trim("test6", 0, 2);
        System.out.println("test6:" + list.range("test6", 0, list.size("test6") - 1));
    }

    @Test
    public void test7() {
        SetOperations<String, String> set = stringRedisTemplate.opsForSet();
        set.add("test7_1", "2", "1", "2", "3", "4", "4", "3");
        set.add("test7_2", "2", "6", "2", "3", "7", "6", "5");
        System.out.println("test7 全部成员:" + set.members("test7_1"));
        System.out.println("test7 差值:" + set.difference("test7_1", "test7_2"));
        System.out.println("test7 交集:" + set.intersect("test7_1", "test7_2"));
        System.out.println("test7 并集:" + set.union("test7_1", "test7_2"));
    }


    @Test
    public void test8() {
        ZSetOperations<String, String> zSet = stringRedisTemplate.opsForZSet();
        zSet.add("test8", "user1", 9);
        zSet.add("test8", "user2", 1);
        zSet.add("test8", "user3", 5);
        zSet.add("test8", "user4", 10);
//        zSet.incrementScore("test8", "use1", 1);
        System.out.println("test8:" + zSet.reverseRange("test8", 0, zSet.size("test8") - 1));
    }


}
