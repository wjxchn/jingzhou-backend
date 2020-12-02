package jingzhou.Service;

import jingzhou.POJO.Paper;
import org.bson.types.ObjectId;

import java.util.List;

public interface PaperService {

    //根据论文ID精确查找
    Paper getById(String id);

    //根据论文title模糊查找
    Paper getByTitle(String title);


    List<Paper> getByFuzzyTitle(String title);
}
