package jingzhou.POJO;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@Data
@Document(collection = "mag_author_0_1")
public class Author implements Serializable {

    @MongoId
    private ObjectId _id;

    @Field("id")
    private long id;

    @Field("pubs")
    private ArrayList<pubs> pubsList;

    //发表总数
    @Field("n_pubs")
    private int pubnum;

    @Field("name")
    private String name;

    private String normalized_name;

    @Field("n_citation")
    private int n_citation;
}
