package javaredis;

import redis.clients.jedis.Jedis;

public class Connect {

	public static void main(String[] args) {
		Jedis client = new Jedis("localhost");
		client.auth("supersecret");
		client.lpush("somelistkey", "somevalue");
		String value = client.lpop("somelistkey");
		System.out.println(value);
	}

}
