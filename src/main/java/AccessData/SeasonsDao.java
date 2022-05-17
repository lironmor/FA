package AccessData;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;

public class SeasonsDao implements Dao {
    private static SeasonsDao instance = new SeasonsDao();
    private MongoDB db = MongoDB.getInstance();
    private MongoCollection teamCollection = db.getCollection("seasons");
    private SeasonsDao(){ }

    public static SeasonsDao getInstance(){
        return instance;
    }

    @Override
    public Document get(String id) {
        Document seasObj = new Document("seasonId", id);
        MongoCursor<Document> cursor = teamCollection.find(seasObj).iterator();
        while (cursor.hasNext()) {
            return cursor.next();
        }
        return null;
    }

    @Override
    public ArrayList<Document> getAll() {
        ArrayList<Document> seasons = new ArrayList<Document>();
        MongoCursor<Document> cursor = teamCollection.find(new Document()).iterator();
        while (cursor.hasNext()) {
            seasons.add(cursor.next());
        }
        return seasons;
    }

    public void save(String seasonName, ArrayList<String> leagues) {
        Document seasObj = new Document("seasonName", seasonName).append("leagues", leagues);
        teamCollection.insertOne(seasObj);
    }
}
