package jingzhou.mongodbdao;

import jingzhou.POJO.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PaperDao extends MongoRepository<Paper, String> {

    List<Paper> findByKeywordsContains(String keyword);
}
