package AccessData;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;

public class LeaguesDao implements Dao{
    private static LeaguesDao instance = new LeaguesDao();
    private MongoDB db = MongoDB.getInstance();
    private MongoCollection teamCollection = db.getCollection("leagues");
    private LeaguesDao(){ }

    public static LeaguesDao getInstance(){
        return instance;
    }

    @Override
    public Document get(String leagueName) {
        Document leagueObj = new Document("_id", leagueName);
        MongoCursor<Document> cursor = teamCollection.find(leagueObj).iterator();
        while (cursor.hasNext()) {
            return cursor.next();
        }
        return null;
    }

    @Override
    public ArrayList<Document> getAll() {
        ArrayList<Document> leagues = new ArrayList<Document>();
        MongoCursor<Document> cursor = teamCollection.find(new Document()).iterator();
        while (cursor.hasNext()) {
            leagues.add(cursor.next());
        }
        return leagues;
    }

    public void save(String leagueID, ArrayList<String> seasons) {
        Document leagueObj = new Document("leagueId", leagueID).append("seasons", seasons);
        teamCollection.insertOne(leagueObj);
    }

    public void save(String leagueName, Object league) {
        Document leagueDoc = new Document("_id", leagueName).append("league", league);
    }
}

