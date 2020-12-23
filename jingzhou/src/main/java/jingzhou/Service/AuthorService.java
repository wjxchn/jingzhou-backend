package jingzhou.Service;

import jingzhou.POJO.Author;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Author> getByRealname(String realname){

        String realnames="^.*"+realname+".*$";
        System.out.println("service : get by realname");
        
        Criteria criteria = new Criteria();
        criteria.and("name").regex(realnames);
        
        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex(realnames));
        List<Author> Authors = mongoTemplate.find(query, Author.class);

        return Authors;
    }

}
