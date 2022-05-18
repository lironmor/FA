package AccessData;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

public class GameDao implements Dao {
    private static GameDao instance = new GameDao();
    private MongoDB db = MongoDB.getInstance();
    private MongoCollection gameCollection = db.getCollection("games");
    private GameDao(){ }

    public static GameDao getInstance(){
        return instance;
    }

    @Override
    public Document get(String gameId) {
        Document teamObj = new Document("_id", gameId);
        MongoCursor<Document> cursor = gameCollection.find(teamObj).iterator();
        while (cursor.hasNext()) {
            return cursor.next();
        }
        return null;
    }

    @Override
    public ArrayList<Document> getAll() {
        ArrayList<Document> games = new ArrayList<Document>();
        MongoCursor<Document> cursor = gameCollection.find(new Document()).iterator();
        while (cursor.hasNext()) {
            games.add(cursor.next());
        }
        return games;
    }

    //    public void save(String teamID, String teamName, int expense, String homeTeamID, String awayTeamID, ArrayList<String> leagues, ArrayList<String> seasons) {
    public void save(String gameID, Date time, int expense, String homeTeamID, String awayTeamID, String stadiumName, String reportId) {
        Document gameObj = new Document("gameId", gameID).append("time", time).append("homeTeam", homeTeamID).append("awayTeam", awayTeamID).append("stadium", stadiumName).append("gameReport", reportId);
        gameCollection.insertOne(gameObj);
    }

    public void save(String gameId, Object game) {
        Document gameDoc = new Document("_id", gameId).append("game", game);
        gameCollection.insertOne(gameDoc);
    }
}
