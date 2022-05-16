package AccessData;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class MongoDB {
    private static MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));;
    private static MongoDatabase database = mongoClient.getDatabase("FADB");; //creates a database inside the client
    private static MongoCollection collection; //collection in the database

    private MongoDB() { }

    private static MongoDB instance = new MongoDB();

    public static MongoDB getInstance() {
        return instance;
    }

    public MongoCollection getCollection(String name) {
        return database.getCollection(name);
    }
}