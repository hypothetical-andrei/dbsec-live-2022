package mongoexperiments;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoInsert {

	public static void main(String[] args) {
		try (MongoClient client = new MongoClient("localhost", 27017)) {
			MongoDatabase db = client.getDatabase("ism");
			MongoCollection<Document> coll = db.getCollection("testcollection");
			Document doc = new Document("type", "bunny")
					.append("characteristics", new Document("size", 15).append("color", "indeterminate"));
			coll.insertOne(doc);
			try (MongoCursor<Document> cursor = coll.find().iterator()) {
				while (cursor.hasNext()) {
					Document document = (Document) cursor.next();
					System.out.println(document.toJson());
				}
			}
		}
	}

}
