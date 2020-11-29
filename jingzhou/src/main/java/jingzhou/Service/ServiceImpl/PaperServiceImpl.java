package jingzhou.Service.ServiceImpl;

import jingzhou.POJO.Paper;
import jingzhou.Service.PaperService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public Paper getById(String id) {

        Query  query = new Query(Criteria.where("_id").is(id));
        Paper paper = mongoTemplate.findOne(query, Paper.class);
        return paper;

    }

    @Override
    public Paper getByTitle(String title) {
        Query query = new Query(Criteria.where("title").is(title));
        Paper paper = mongoTemplate.findOne(query, Paper.class);
        return paper;
    }
}
