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
    public Document get(String stadiumId) {
        Document stadiumObj = new Document("stadiumId", stadiumId);
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

    //    public void save(String teamID, String teamName, int expense, String homeTeamID, String awayTeamID, ArrayList<String> leagues, ArrayList<String> seasons) {
    public void save(String stadiumID, String stadiumName, String location) {
        Document stadiumObj = new Document("stadiumId", stadiumID).append("stadiumName", stadiumName).append("location", location);
        stadiumCollection.insertOne(stadiumObj);
    }
}
