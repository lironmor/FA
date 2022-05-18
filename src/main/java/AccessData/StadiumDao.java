package AccessData;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;

public class StadiumDao implements Dao {
    private static StadiumDao instance = new StadiumDao();
    private MongoDB db = MongoDB.getInstance();
    private MongoCollection stadiumCollection = db.getCollection("stadiums");
    private StadiumDao(){ }

    public static StadiumDao getInstance(){
        return instance;
    }

    @Override
    public Document get(String stadiumName) {
        Document stadiumObj = new Document("_id", stadiumName);
        MongoCursor<Document> cursor = stadiumCollection.find(stadiumObj).iterator();
        while (cursor.hasNext()) {
            return cursor.next();
        }
        return null;
    }

    @Override
    public ArrayList<Document> getAll() {
        ArrayList<Document> stadiums = new ArrayList<Document>();
        MongoCursor<Document> cursor = stadiumCollection.find(new Document()).iterator();
        while (cursor.hasNext()) {
            stadiums.add(cursor.next());
        }
        return stadiums;
    }

    public void save(String stadiumName, Object stadium) throws Exception{
        if(stadiumName == null || stadium == null) {
            throw new Exception("Document parameters are null");
        }
        Document stadiumDoc = new Document("_id", stadiumName).append("stadium", stadium);
        stadiumCollection.insertOne(stadiumDoc);
    }
}
