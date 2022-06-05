package mongoexperiments;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MapReduceIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MapReduce {

	public static void main(String[] args) {
		try (MongoClient client = new MongoClient("localhost", 27017)) {
			MongoDatabase db = client.getDatabase("ism");
			MongoCollection<Document> coll = db.getCollection("students");
			String mapFunction = "function() { emit(this.name, this.grade); };";
			String reduceFunction = "function(keyName, values) { return Array.avg(values);};";
			MapReduceIterable<Document> results = coll.mapReduce(mapFunction, reduceFunction);
			for (Document result: results) {
				System.out.println(result);
			}
		}
	}

}
