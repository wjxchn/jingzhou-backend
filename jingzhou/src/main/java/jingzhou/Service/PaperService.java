package jingzhou.Service;

import jingzhou.POJO.Paper;
import org.bson.types.ObjectId;

public interface PaperService {

    Paper getById(ObjectId id);
}
