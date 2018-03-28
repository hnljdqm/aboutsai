package com.aboutsai;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.aboutsai.blog.service.UserService;

/**
 * mybatis分页查询测试类
 * @author hnljd
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
	private Logger logger = LoggerFactory.getLogger(UserTest.class);
	@Autowired
	private UserService userService;
	
	@Test
	public void testQueryByPage() {
		/*PageInfo<User> pageInfo = userService.query(new User(), 2, 2);
		logger.debug("=========================\n" + pageInfo.toString());
		logger.debug(JSON.toJSONString(pageInfo));*/
	}

}
