package jingzhou.Dao;

import jingzhou.POJO.Author;
import org.apache.ibatis.annotations.Mapper;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

@Mapper
public interface AuthorDao extends MongoRepository<Author,Long> {

    Author getAuthorById(ObjectId _id);

    Author getAllByName(String name);
}
