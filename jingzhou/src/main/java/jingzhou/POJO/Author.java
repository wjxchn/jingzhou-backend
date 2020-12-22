package jingzhou.POJO;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.ArrayList;
@Data
@Document(indexName = "testdoct", indexStoreType = "author")
public class Author implements Serializable {

    @Id
    private ObjectId _id;

    private long id;

    private ArrayList<Pubs> pubsList;

    //发表总数
    private int pubnum;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String name;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String normalized_name;

    private int n_citation;
}
