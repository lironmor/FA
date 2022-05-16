package AccessData;

import Domain.Game;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import Domain.Referee;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


public class RefereeDao implements Dao<Referee> {
    private MongoDB db = MongoDB.getInstance();
    private MongoCollection usersCollection = db.getCollection("users");
    private MongoCollection refereesCollection = db.getCollection("referees");


    private RefereeDao() {
    }

    public static RefereeDao getInstance() {
        return instance;
    }

    private static RefereeDao instance = new RefereeDao();

    @Override
    public Referee get(String id) {
        Document refObj = new Document("fullName", id);
        MongoCursor<Document> cursor = refereesCollection.find(refObj).iterator();
        while (cursor.hasNext()) {
            Document next = cursor.next();
            try {
                ArrayList<Game> comingUp = new ArrayList<>();
                for (Document document : (List<Document>) next.get("comingUp")) {
                    Game game = new Game(document.getString("gameId"), document.getDate("time"));
                    comingUp.add(game);
                }
                Referee referee = new Referee(next.getString("fullName"), next.getString("email"), next.getString("userName"), next.getString("password"), next.getString("role"), next.getString("degree"), comingUp);
                return referee;
            } catch (Exception e) {
            }
        }
        return null;
    }

    @Override
    public ArrayList<Referee> getAll() {
        return null;
    }

//    @Override
//    public ArrayList<Referee> getAll() {
//        ArrayList<Referee> users = new ArrayList<Referee>();
//        MongoCursor<Document> cursor = collection.find(new Document()).iterator();
//        while (cursor.hasNext()) {
//            Document next = cursor.next();
//            //            User user = new User(...)
//            //            users.add(user)
//        }
//        return users;
//    }

    @Override
    public void save(Referee referee) {
        ArrayList<Game> comingUp = referee.getComingUp();
        ArrayList<Document> toDoc = new ArrayList<>();
        for (Game game : comingUp) {
//            toDoc.add(new Document("gameId", game.getGameID()).append("homeTeamID", game.getHomeTeam().getTeamID()).append("awayTeamID", game.getAwayTeam().getTeamID()).append("time", game.getTimeAndDate()));
            toDoc.add(new Document("gameId", game.getGameID()).append("time", game.getTimeAndDate()));

        }
        Document refObj = new Document("fullName", referee.getUserName()).append("email", referee.getEmail()).append("userName", referee.getUserName()).append("password", referee.getPassword()).append("role", referee.getRefereeRole()).append("degree", referee.getDegree()).append("comingUp", toDoc);
        refereesCollection.insertOne(refObj);
    }
//        BasicDBObject userObj = new BasicDBObject("id", user.getId()).append("name", user.getName());
//        collection.insertOne(userObj);
    public static void main(String[] args) {
        RefereeDao refereeDao = RefereeDao.getInstance();
        Referee referee = null;
        try {
            referee = new Referee("aaa", "aaa", "aaa", "aaa", "main", "expert", new ArrayList<Game>());
        } catch (Exception e) { }
        refereeDao.save(referee);
        System.out.println(refereeDao.get("aaa").getFullName());
    }
}



