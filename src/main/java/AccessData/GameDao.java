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


    public void save(String gameId, Object game) {
        Document gameDoc = new Document("_id", gameId).append("game", game);
        gameCollection.insertOne(gameDoc);
    }

    public void update(String gameId, Object game) {
        Document gameDoc = new Document("_id", gameId);
        gameCollection.findOneAndDelete(gameDoc);
        save(gameId, game);
    }
}
