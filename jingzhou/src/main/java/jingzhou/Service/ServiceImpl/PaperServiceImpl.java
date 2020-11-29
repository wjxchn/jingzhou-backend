package jingzhou.Service.ServiceImpl;

import jingzhou.POJO.Paper;
import jingzhou.Service.PaperService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public Paper getById(ObjectId id) {
        return mongoTemplate.findById(id,Paper.class);
    }
}
