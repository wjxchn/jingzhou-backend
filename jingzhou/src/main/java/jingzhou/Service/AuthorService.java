package jingzhou.Service;

import jingzhou.POJO.Author;
import jingzhou.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    private MongoTemplate mongoTemplate;

    public Author getByName(String name) {
//        Query query = new Query(Criteria.where("name").is(name));
//        return mongoTemplate.findOne(query,Author.class);
        return authorRepository.findByName(name);
    }


    public Author getByRealId(String id) {
//        Query query = new Query(Criteria.where("id").is(id));
//
//        return mongoTemplate.findOne(query,Author.class);


        /*这个地方可能会有问题*/
        return authorRepository.findByAuthorid(id);
    }


}
