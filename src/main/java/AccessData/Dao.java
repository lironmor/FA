package AccessData;

import java.util.ArrayList;

public interface Dao <T> { //data access object
    T get(String id);

    ArrayList<T> getAll();

    void save(T t);

//    void update(T t, String[] params); //optional
//
//    void delete(T t); //optional
}
