package mongoexperiments;

import java.util.ArrayList;
import java.util.Arrays;

import org.bson.Document;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoAggregate {

	public static void main(String[] args) {
		try (MongoClient client = new MongoClient("localhost", 27017)) {
			MongoDatabase db = client.getDatabase("ism");
			MongoCollection<Document> coll = db.getCollection("testcollection");
			List<Document> pipeline = new ArrayList<>();
			pipeline.add(Document.parse("{$match: {type: 'bunny'}}"));
			pipeline.add(Document.parse("{$group: {_id: 'count', count: {$sum: 1}}}"));
			AggregateIterable<Document> results = coll.aggregate(pipeline);
			for (Document document : results) {
				System.out.println(document);
			}
		}
	}

}
