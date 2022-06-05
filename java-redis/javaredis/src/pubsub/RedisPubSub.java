package pubsub;

import redis.clients.jedis.Jedis;

public class RedisPubSub {

	public static void main(String[] args) throws InterruptedException {
		MessageListener listener =  new MessageListener();
		
		Thread sub = new Thread(() -> {
			Jedis subj = new Jedis("localhost");
			subj.subscribe(listener, "test");
			subj.quit();
		});
		
		sub.start();

		Thread pub = new Thread(() -> {
			Jedis pubj = new Jedis("localhost");
			for (int i = 0; i < 10; i++) {
				pubj.publish("test", "This is message " + i);
			}
			pubj.quit();
		});
		
		pub.start();
		pub.join();
	}

}
