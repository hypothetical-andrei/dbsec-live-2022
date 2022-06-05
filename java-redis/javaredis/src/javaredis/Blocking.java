package javaredis;

import java.util.List;

import redis.clients.jedis.Jedis;

public class Blocking {

	public static void main(String[] args) {
		Jedis client = new Jedis("localhost");
		while (true) {
			List<String> values = client.blpop(0, "blocking");
			System.out.println(values);
		}
	}

}
