package AccessData;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;


import java.util.ArrayList;


public class TeamDao implements Dao {

    private static TeamDao instance = new TeamDao();
    private MongoDB db = MongoDB.getInstance();
    private MongoCollection teamCollection = db.getCollection("teams");
    private TeamDao(){ }

    public static TeamDao getInstance(){
        return instance;
    }

    @Override
    public Document get(String id) {
        Document teamObj = new Document("teamId", id);
        MongoCursor<Document> cursor = teamCollection.find(teamObj).iterator();
        while (cursor.hasNext()) {
            return cursor.next();
        }
        return null;
    }

    @Override
    public ArrayList<Document> getAll() {
        ArrayList<Document> teams = new ArrayList<Document>();
        MongoCursor<Document> cursor = teamCollection.find(new Document()).iterator();
        while (cursor.hasNext()) {
            teams.add(cursor.next());
        }
        return teams;
    }

    public void save(String teamID, String teamName, int expense, ArrayList<String> assets, String league, String season) {
        Document teamObj = new Document("teamId", teamID).append("teamName", teamName).append("expense", expense).append("assets", assets).append("league", league).append("season", season);
        teamCollection.insertOne(teamObj);
    }
}
