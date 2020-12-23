package jingzhou.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//import org.springframework.data.mongodb.core.mapping.Field;
@Data
@Document(indexName = "jingzhou.author")
public class Author implements Serializable {

    @Id @JsonProperty("id")
    private String id;

    @Field(name = "authorid", analyzer = "ik_smart", type = FieldType.Text)@JsonProperty("authorid")
    private String authorid;

    @Field(name = "pubs")@JsonProperty("pubs")
    private ArrayList<Pubs> pubsList;

    //发表总数
    @Field(name = "n_pubs")@JsonProperty("n_pubs")
    private int pubnum;

    @Field(analyzer = "ik_smart", type = FieldType.Text)@JsonProperty("name")
    private String name;

    @Field(analyzer = "ik_smart", type = FieldType.Text)@JsonProperty("normalized_name")
    private String normalized_name;

    @JsonProperty("n_citation")
    private int n_citation;
    @JsonProperty("h_index")
    private int h_index;
    @JsonProperty("orgs")@Field(analyzer = "ik_smart", type = FieldType.Text)
    private List<String> orgs;
    @JsonProperty("org")@Field(analyzer = "ik_smart", type = FieldType.Text)
    private List<String> org;
    @JsonProperty("position")@Field(analyzer = "ik_smart", type = FieldType.Keyword)
    private String position;
    @JsonProperty("tags")
    private List<Tag> tags;
}
