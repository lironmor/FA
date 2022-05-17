package AccessData;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;

public class UserDao implements Dao {
    private static UserDao instance = new UserDao();
    private MongoDB db = MongoDB.getInstance();
    private MongoCollection userCollection = db.getCollection("user");
    private UserDao(){ }

    public static UserDao getInstance(){
        return instance;
    }

    @Override
    public Document get(String userName) {
        Document userObj = new Document("userName", userName);
        MongoCursor<Document> cursor = userCollection.find(userObj).iterator();
        while (cursor.hasNext()) {
            return cursor.next();
        }
        return null;
    }

    @Override
    public ArrayList<Document> getAll() {
        ArrayList<Document> users = new ArrayList<Document>();
        MongoCursor<Document> cursor = userCollection.find(new Document()).iterator();
        while (cursor.hasNext()) {
            users.add(cursor.next());
        }
        return users;
    }

    //    public void save(String teamID, String teamName, int expense, String homeTeamID, String awayTeamID, ArrayList<String> leagues, ArrayList<String> seasons) {
    public void save(String userName, String password, String userType) {
        Document teamObj = new Document("userName", userName).append("password", password).append("type", userType);
        userCollection.insertOne(teamObj);
    }
}
