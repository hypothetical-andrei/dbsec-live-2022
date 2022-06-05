package mongoexperiments;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Connect {
	
	public static void main(String[] args) {
		try (MongoClient client = new MongoClient("localhost", 27017)) {
			MongoDatabase db = client.getDatabase("ism");
			MongoCollection<Document> coll = db.getCollection("testcollection");
			Document first = coll.find().first();
			System.out.println(first);
		}
	}
}
