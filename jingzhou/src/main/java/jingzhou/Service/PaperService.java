package jingzhou.Service;

import jingzhou.POJO.Paper;
import jingzhou.mongodbdao.PaperDao;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class PaperService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    PaperDao paperDao;

    public Paper getById(String id) {

        Query query = new Query(Criteria.where("id").is(id));
        Paper paper = mongoTemplate.findOne(query, Paper.class);
        return paper;

    }


    public Paper getByTitle(String title) {
        Query query = new Query(Criteria.where("title").is(title));
        Paper paper = mongoTemplate.findOne(query, Paper.class);
        return paper;
    }


    public List<Paper> getByFuzzyTitle(String title, int pagenum) {

        String titles="^.*"+title+".*$";
        Criteria criteria = new Criteria();
        criteria.and("title").regex(titles);

        Query query = new Query();
        query.addCriteria(Criteria.where("title").regex(titles));
        //50为一页的数量
        if (pagenum != 1){
            int num = (pagenum-1)*20;
            query.limit(num);
            List<Paper> lastpapers = mongoTemplate.find(query, Paper.class);
            Paper last = lastpapers.get(lastpapers.size()-1);
            ObjectId _id = last.get_id();
            System.out.println("--------_id: "+_id);
            criteria.and("_id").gt(_id);

        }

        query.addCriteria(criteria).limit(20);

        List<Paper> papers = mongoTemplate.find(query, Paper.class);
        return papers;
    }

    public List<Paper> getByKeyword(String keyword, int pagenum){

        System.out.println("service : get by keyword");
        Criteria criteria = new Criteria();
        Query query = new Query();
        query.addCriteria(Criteria.where("keywords").is(keyword)).limit(20);
        //50为一页的数量
      if (pagenum != 1){
            int num = (pagenum-1)*20;
            query.limit(num);
            List<Paper> lastpapers = mongoTemplate.find(query, Paper.class);
            Paper last = lastpapers.get(lastpapers.size()-1);
            ObjectId _id = last.get_id();
            System.out.println("--------_id: "+_id);
            criteria.and("_id").gt(_id);

        }

        query.addCriteria(criteria).limit(20);
        List<Paper> papers = mongoTemplate.find(query, Paper.class);

        return papers;
    }

    public List<Paper> getByAuthor(String auhtorname, int pagenum){

        System.out.println("service : get by keyword");
        Criteria criteria = new Criteria();
        Query query = new Query();
        query.addCriteria(Criteria.where("authors.name").is(auhtorname)).limit(20);
        //50为一页的数量
        if (pagenum != 1){
            int num = (pagenum-1)*20;
            query.limit(num);
            List<Paper> lastpapers = mongoTemplate.find(query, Paper.class);
            Paper last = lastpapers.get(lastpapers.size()-1);
            ObjectId _id = last.get_id();
            System.out.println("--------_id: "+_id);
            criteria.and("_id").gt(_id);

        }

        query.addCriteria(criteria).limit(20);
        List<Paper> papers = mongoTemplate.find(query, Paper.class);

        return papers;
    }
}
