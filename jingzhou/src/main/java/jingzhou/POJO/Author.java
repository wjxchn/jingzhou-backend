package jingzhou.POJO;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.ArrayList;

//import org.springframework.data.mongodb.core.mapping.Field;
@Data
@Document(indexName = "testdoct", indexStoreType = "author")
public class Author implements Serializable {


    private ObjectId document;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    @Column(name = "id")
    private String authorid;

    private ArrayList<Pubs> pubsList;

    //发表总数
    private int pubnum;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String name;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String normalized_name;

    private int n_citation;
}
