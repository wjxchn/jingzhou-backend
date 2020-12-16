package jingzhou.Service;

import jingzhou.POJO.Author;
import jingzhou.repository.AuthUserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private MongoTemplate mongoTemplate;

    public Author getByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query,Author.class);
    }


    public Author getByRealId(String id) {
        Query query = new Query(Criteria.where("id").is(id));

        return mongoTemplate.findOne(query,Author.class);
    }

}
