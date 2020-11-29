package jingzhou.Service;

import jingzhou.POJO.Author;
import org.bson.types.ObjectId;

public interface AuthorService {
    Author getByName(String name);


    Author getById(ObjectId _id);
}
