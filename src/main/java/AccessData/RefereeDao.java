package AccessData;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import org.bson.Document;

import java.util.ArrayList;


public class RefereeDao implements Dao {
    private MongoDB db = MongoDB.getInstance();
    private UserDao userDao = UserDao.getInstance();
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

    @Override
    public ArrayList<Document> getAll() {
        ArrayList<Document> referees = new ArrayList<Document>();
        MongoCursor<Document> cursor = refereesCollection.find(new Document()).iterator();
        while (cursor.hasNext()) {
            referees.add(cursor.next());
        }
        return referees;
    }

    public void save(Object referee, String userName, String password, String email) throws Exception{
        if(referee == null || userName == null || password == null) {
            throw new Exception("Document parameters are null");
        }
        Document refObj = new Document("_id", userName).append("ref", referee);
        refereesCollection.insertOne(refObj);
        userDao.save(userName, password, "referee", email);
    }
}




