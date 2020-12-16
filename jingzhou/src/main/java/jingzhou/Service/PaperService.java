package jingzhou.Service;

import jingzhou.POJO.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class PaperService {

    @Autowired
    MongoTemplate mongoTemplate;



    public Paper getById(String id) {

        Query query = new Query(Criteria.where("_id").is(id));
        Paper paper = mongoTemplate.findOne(query, Paper.class);
        return paper;

    }


    public Paper getByTitle(String title) {
        Query query = new Query(Criteria.where("title").is(title));
        Paper paper = mongoTemplate.findOne(query, Paper.class);
        return paper;
    }


    public List<Paper> getByFuzzyTitle(String title) {

        Pattern pattern = Pattern.compile("^.*" + title + ".*$", Pattern.CASE_INSENSITIVE);
        Query query = Query.query(Criteria.where(title).regex(pattern));
        List<Paper> papers = mongoTemplate.find(query, Paper.class);
        return papers;

    }
}
