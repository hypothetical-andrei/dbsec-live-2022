package javaredis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class MultiExec {

	public static void main(String[] args) {
		Jedis client = new Jedis("localhost");
		Transaction t = client.multi();
		t.lpush("somekey", "somevalue1");
		t.lpush("somekey", "somevalue2");
		t.exec();
	}

}
