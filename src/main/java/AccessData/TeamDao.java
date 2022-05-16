package AccessData;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import Domain.Team;

import java.util.ArrayList;


public class TeamDao implements Dao<Team>{

    private static TeamDao instance = new TeamDao();
    private MongoDB db = MongoDB.getInstance();
    private MongoCollection collection = db.getCollection("Teams");
    private TeamDao(){ }

    public static TeamDao getInstance(){
        return instance;
    }

    @Override
    public Team get(String id) {
        BasicDBObject teamObj = new BasicDBObject("id", id);
        MongoCursor<BasicDBObject> cursor = collection.find(teamObj).iterator();
        while (cursor.hasNext()) {
            BasicDBObject next = cursor.next();
//            Team team = new Team(next.get("id"),...)
//            return team;
        }
        return null;
    }

    @Override
    public ArrayList<Team> getAll() {
        ArrayList<Team> teams = new ArrayList<Team>();
        MongoCursor<BasicDBObject> cursor = collection.find(new BasicDBObject()).iterator();
        while (cursor.hasNext()) {
            BasicDBObject next = cursor.next();
            //            Team team = new Team(...)
            //            teams.add(team)
        }
        return teams;
    }

    @Override
    public void save(Team team) {
//        BasicDBObject teamObj = new BasicDBObject("id", team.getId()).append("name", team.getName());
//        collection.insertOne(teamObj);
    }
}
