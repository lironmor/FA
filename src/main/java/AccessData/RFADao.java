package AccessData;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;

public class RFADao implements Dao {
    private MongoDB db = MongoDB.getInstance();
    private MongoCollection FACollection = db.getCollection("fa");
    private UserDao userDao = UserDao.getInstance();


    private RFADao() { }

    public static RFADao getInstance() {
        return instance;
    }

    private static RFADao instance = new RFADao();

    @Override
    public Document get(String userName) {
        Document faObj = new Document("_id", userName);
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


    public void save(Object rfa, String userName, String password, String email) throws Exception{
        if(rfa == null || userName == null || password == null) {
            throw new Exception("Document parameters are null");
        }
        Document rfaObj = new Document("_id", userName).append("rfa", rfa);
        FACollection.insertOne(rfaObj);
        userDao.save(userName, password, "rfa", email);
    }
}
