package AccessData;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;


public class MongoDB {
    private static MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));;
    private static CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), org.bson.codecs.configuration.CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    private static MongoDatabase database = mongoClient.getDatabase("FADB").withCodecRegistry(pojoCodecRegistry);; //creates a database inside the client

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