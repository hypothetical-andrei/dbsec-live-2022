package mongoexperiments;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Auth {

	public static void main(String[] args) {
		MongoClientURI uri = new MongoClientURI("mongodb://someuser:somepass@localhost:27017/?authSource=ismwrite");
		try (MongoClient client = new MongoClient(uri)) {
			MongoDatabase db = client.getDatabase("ismwrite");
			MongoCollection<Document> coll = db.getCollection("stuff");
			System.out.println(coll.count());
		}
	}

}
