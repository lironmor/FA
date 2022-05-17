package AccessData;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
public class AssetsDao implements Dao {
    private static AssetsDao instance = new AssetsDao();
    private MongoDB db = MongoDB.getInstance();
    private MongoCollection assetsCollection = db.getCollection("assets");
    private AssetsDao(){ }

    public static AssetsDao getInstance(){
        return instance;
    }

    @Override
    public Document get(String assetId) {
        Document assetObj = new Document("assetId", assetId);
        MongoCursor<Document> cursor = assetsCollection.find(assetObj).iterator();
        while (cursor.hasNext()) {
            return cursor.next();
        }
        return null;
    }

    @Override
    public ArrayList<Document> getAll() {
        ArrayList<Document> assets = new ArrayList<Document>();
        MongoCursor<Document> cursor = assetsCollection.find(new Document()).iterator();
        while (cursor.hasNext()) {
            assets.add(cursor.next());
        }
        return assets;
    }

    public void save(String assetID, String assetType) {
        Document assetObj = new Document("assetId", assetID).append("assetType", assetType);
        assetsCollection.insertOne(assetObj);
    }
}
