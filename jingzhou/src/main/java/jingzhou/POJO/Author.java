package jingzhou.POJO;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.ArrayList;

//import org.springframework.data.mongodb.core.mapping.Field;
@Data
@Document(indexName = "jingzhou.author")
public class Author implements Serializable {


    @Field(name = "id", analyzer = "ik_smart", type = FieldType.Text)
    private String authorid;

    @Field(name = "pubs")
    private ArrayList<Pubs> pubsList;

    //发表总数
    @Field(name = "n_pubs")
    private int pubnum;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String name;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String normalized_name;

    private int n_citation;
}
