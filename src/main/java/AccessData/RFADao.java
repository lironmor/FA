package AccessData;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;

public class RFADao implements Dao {
    private MongoDB db = MongoDB.getInstance();
    private MongoCollection usersCollection = db.getCollection("users");
    private MongoCollection FACollection = db.getCollection("fa");


    private RFADao() { }

    public static RFADao getInstance() {
        return instance;
    }

    private static RFADao instance = new RFADao();

    @Override
    public Document get(String userName) {
        Document faObj = new Document("userName", userName);
        MongoCursor<Document> cursor = FACollection.find(faObj).iterator();
        while (cursor.hasNext()) {
            return cursor.next();
        }
        return null;
    }


    @Override
    public ArrayList<Document> getAll() {
        ArrayList<Document> fas = new ArrayList<Document>();
        MongoCursor<Document> cursor = FACollection.find(new Document()).iterator();
        while (cursor.hasNext()) {
            fas.add(cursor.next());
        }
        return fas;
    }

    public void save(String name, String email, String userName, String password) {
        Document faObj = new Document("name", name).append("email", email).append("userName", userName).append("password", password);
        FACollection.insertOne(faObj);
        Document userObj = new Document("userName", userName).append("password", password).append("type", "fa");
        usersCollection.insertOne(userObj);
    }
}
