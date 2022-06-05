package mongoexperiments;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DeleteUpdate {

	public static void main(String[] args) {
		try (MongoClient client = new MongoClient("localhost", 27017)) {
			MongoDatabase db = client.getDatabase("ism");
			MongoCollection<Document> coll = db.getCollection("test2");
			coll.insertOne(Document.parse("{ a: 'test', b: 1 }"));
			coll.updateOne(Document.parse("{ b: 1 }"), Document.parse("{ $set: { a: 'modified' } }"));
			Document d = coll.find(Document.parse("{ a: 'modified' }")).first();
			System.out.println(d);
			coll.deleteOne(Document.parse("{ a: 'modified' }"));
			d = coll.find(Document.parse("{ a: 'modified' }")).first();
			System.out.println(d);
		}
	}

}
