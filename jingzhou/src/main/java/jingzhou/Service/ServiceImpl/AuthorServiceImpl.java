package jingzhou.Service.ServiceImpl;

import jingzhou.Dao.AuthorDao;
import jingzhou.POJO.Author;
import jingzhou.Service.AuthorService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Resource
    private MongoTemplate mongoTemplate;
    @Autowired
    private AuthorDao authorDao;
    @Override
    public Author getByName(String name) {
        return authorDao.getAllByName(name);
    }

    @Override
    public Author getByRealId(String id) {
        Query query = new Query(Criteria.where("id").is(id));

        return mongoTemplate.findOne(query,Author.class);
    }

    @Override
    public Author getById(ObjectId _id) {
        System.out.println("get author at authorserviceimpl");
        return mongoTemplate.findById(_id,Author.class);
    }
}