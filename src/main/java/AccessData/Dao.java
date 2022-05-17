package AccessData;

import org.bson.Document;

import java.util.ArrayList;

public interface Dao { //data access object
    Document get(String id);

    ArrayList<Document> getAll();
}
