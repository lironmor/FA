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
    public Document get(String seasonId) {
        Document seasObj = new Document("_id", seasonId);
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

    public void save(String seasonId, Object season) {
        Document seasonDoc = new Document("_id", seasonId).append("season", season);
        teamCollection.insertOne(seasonDoc);
    }
}
