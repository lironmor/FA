package AccessData;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import org.bson.Document;

import java.util.ArrayList;


public class RefereeDao implements Dao {
    private MongoDB db = MongoDB.getInstance();
    private MongoCollection usersCollection = db.getCollection("users");
    private MongoCollection refereesCollection = db.getCollection("referees");


    private RefereeDao() {
    }

    public static RefereeDao getInstance() {
        return instance;
    }

    private static RefereeDao instance = new RefereeDao();

    @Override
    public Document get(String userName) {
        Document refObj = new Document("_id", userName);
        MongoCursor<Document> cursor = refereesCollection.find(refObj).iterator();
        while (cursor.hasNext()) {
            return cursor.next();
        }
        return null;
    }


//    public Document gett(int id) {
//        Document refObj = new Document("_id", id);
//        MongoCursor<Document> cursor = refereesCollection.find(refObj).iterator();
//        while (cursor.hasNext()) {
//            return (Document) cursor.next().get("ref");
//        }
//        return null;
//    }

    @Override
    public ArrayList<Document> getAll() {
        ArrayList<Document> referees = new ArrayList<Document>();
        MongoCursor<Document> cursor = refereesCollection.find(new Document()).iterator();
        while (cursor.hasNext()) {
            referees.add(cursor.next());
        }
        return referees;
    }

//    public void save(String name, String email, String userName, String password, String degree, String role, ArrayList<String> comingUpIds) {
//        Document refObj = new Document("name", name).append("email", email).append("userName", userName).append("password", password).append("role", role).append("degree", degree).append("comingUp", comingUpIds);
//        refereesCollection.insertOne(refObj);
//        Document userObj = new Document("userName", userName).append("password", password).append("type", "referee");
//        usersCollection.insertOne(userObj);
//    }

    public void save(Object referee, String userName, String password) {
        Document refObj = new Document("_id", userName).append("ref", referee);
        refereesCollection.insertOne(refObj);
        Document userObj = new Document("userName", userName).append("password", password).append("type", "referee");
        usersCollection.insertOne(userObj);
    }
}




