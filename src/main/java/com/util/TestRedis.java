package com.util;

import redis.clients.jedis.Jedis;

public class TestRedis {

	public static void main(String[] args) {
		
		// 連接本地 Redis
        Jedis jedis = new Jedis("localhost",6379);
        
        // 可以不用設定
        // jedis.auth("密碼");
        System.out.println("Connection Successfully");
        
        // 測試是否運行
        System.out.println("Server is running " + jedis.ping());
        
        jedis.close();
    }
}
