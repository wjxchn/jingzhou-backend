package jingzhou.Dao;

import jingzhou.POJO.Paper;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;


public interface PaperDao extends MongoRepository<Paper,Long> {

}
