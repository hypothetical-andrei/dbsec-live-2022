package mongoexperiments;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class InsertMany {

	public static void main(String[] args) {
		try (MongoClient client = new MongoClient("localhost", 27017)) {
			MongoDatabase db = client.getDatabase("ism");
			MongoCollection<Document> coll = db.getCollection("testcollection");
			Random r = new Random();
			List<Document> documents = new ArrayList<>();
			for (int i = 0; i < 50; i++) {
				Document doc = new Document("type", "bunny")
						.append("characteristics", new Document("size", r.nextInt()).append("color", "indeterminate"));				
				documents.add(doc);
			}
			coll.insertMany(documents);
			Long count = coll.count();
			System.out.println("there are now " + count + "bunnies");
		}
	}

}
