package mongoexperiments;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoJson {

	public static void main(String[] args) {
		try (MongoClient client = new MongoClient("localhost", 27017)) {
			MongoDatabase db = client.getDatabase("ism");
			MongoCollection<Document> coll = db.getCollection("testcollection");
			Document d = coll.find(Document.parse("{type : 'bunny'}")).first();
			System.out.println(d);
		}
	}

}
