package com.adcc.mpdemo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.adcc.mpdemo.controller.RedisUtils;
import com.adcc.mpdemo.entity.User;
import com.adcc.mpdemo.mapper.UserMapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;

import lombok.val;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MpdemoApplication.class)
@Log4j2
@Slf4j
public class MpdemoApplicationTest {
	private Long starttime;
	@Rule
	public TestName junitClass = new TestName();

	@Before
	public void before(){
		starttime = System.currentTimeMillis();
        System.out.println(junitClass.getMethodName() + "....................start....................");
	}


	@Autowired
	private UserMapper userMapper;

	@Test
	public void testSelect() {
		System.out.println("-----------SelectAll test----------");
		List<User> userList = userMapper.selectList(null);
		int size=userList.size();
		Assert.isTrue(size == userList.size(), "");//断言这个结果为12，否则抛出异常
		userList.forEach(System.out::println);
		log.info(junitClass.getMethodName() + "测试");
	}

	@Test
	public void findTest() throws Exception {
		List<User> list = userMapper.selectList(null);
		log.info("print: "+ list);


	}

	@Test
    public void testInsert() {
        User userEntity = new User();
        userEntity.setName("张数");
        userEntity.setAge(17);
        userEntity.setEmail("ppz@qq.com");
        int count = userMapper.insert(userEntity);

        System.out.println(count);
        System.out.println(userEntity);
    }


	@Autowired
    RedisUtils redisUtils;
    
    @Test
    void redisTestLoads() {
        redisUtils.set("test:key:name", "zhang.can");
        redisUtils.set("test:key:year", "2026");
        System.out.println(redisUtils.getString("test:key:name2"));
		System.out.println("缓存设置成功");
    }

	

	@After
    public void after() {
        double usedtime = (System.currentTimeMillis() - starttime) / 1000.0;
        System.out.println("耗时  " + usedtime + " my");
        System.out.println(junitClass.getMethodName() + "....................end....................");
    }

}
