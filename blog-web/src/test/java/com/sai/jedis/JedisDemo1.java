package com.sai.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisDemo1 {
	/**
	 * 直接连接redis
	 * @author hnljd
	 * @date 2018年6月2日 上午12:18:14
	 */
	@Test
	public void test1() {
		try {
			// 1.设置IP地址和端口
			Jedis jedis = new Jedis("114.116.12.224", 6379);
			// 2.保存数据
			jedis.set("name", "sai.deng");
			// 3.获取数据
			String name = jedis.get("name");
			System.out.println(name);

			// List<SysUser> userList = new ArrayList<SysUser>();
			// userList.add(new SysUser("214535366@qq.com", "123456"));
			// userList.add(new SysUser("8888@qq.com", "qwe123"));
			// jedis.set .set("userList", userList);
			// 4.释放资源
			jedis.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 连接池方式连接redis
	 * @author hnljd
	 * @date 2018年6月2日 上午12:18:01
	 */
	@Test
	public void test2() {
		// 获取连接池的配置对象
		JedisPoolConfig config = new JedisPoolConfig();
		// 设置最大可连接数
		config.setMaxTotal(30);
		// 设置最大空闲连接数
		config.setMaxIdle(10);
		JedisPool pool = new JedisPool(config, "114.116.12.224", 6379);
		Jedis jedis = null;
		try {
			jedis = pool.getResource();

			jedis.set("mobile", "15050185102");
			String value = jedis.get("mobile");
			System.out.println(value);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
			if (pool != null) {
				pool.close();
			}
		}
	}
}
