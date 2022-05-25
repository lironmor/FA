package AccessData;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;

public class UserDao implements Dao {
    private static UserDao instance = new UserDao();
    private MongoDB db = MongoDB.getInstance();
    private MongoCollection userCollection = db.getCollection("users");
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

    public void save(String userName, String password, String userType, String email) {
        Document teamObj = new Document("userName", userName).append("password", password).append("type", userType).append("email", email);
        userCollection.insertOne(teamObj);
    }

    public void deleteOne(String userName){
        Document teamObj = new Document("userName", userName);
        userCollection.deleteOne(teamObj);
    }

    public boolean isEmailExist(String email) {
        Document userObj = new Document("email", email);
        MongoCursor<Document> cursor = userCollection.find(userObj).iterator();
        while (cursor.hasNext()) {
            return true;
        }
        return false;
    }
}
