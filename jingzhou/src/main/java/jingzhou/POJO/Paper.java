package jingzhou.POJO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.ArrayList;

@Data
@Document(indexName = "jingzhou.paper")
public class Paper implements Serializable {
    @Id @JSONField(name = "id")
    private String _id;

    @Field(analyzer = "ik_smart", type = FieldType.Text)@JSONField(name = "paperid")
    @Column(name = "paperid")
    private String paperid;

    @Field(analyzer = "ik_smart", type = FieldType.Text)@JSONField(name = "title")
    private String title;

    @Field(name = "authors", analyzer = "ik_smart", type = FieldType.Nested)@JSONField(name = "authors")
    private ArrayList<Paper_Author>authors;
    @JSONField(name = "venue")
    private Paper_venue venue;

    private int year;

    @Field(analyzer = "ik_smart", type = FieldType.Nested)
    ArrayList<String> keywords;

    private int n_citation;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String page_start;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String page_end;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String doc_type;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String lang;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String publisher;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String volume;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String issue;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String issn;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String isbn;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String doi;

    @Field(name = "url", analyzer = "ik_smart", type = FieldType.Nested)
    private ArrayList<String> url;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String pdf;

    @Field(name = "abstract", analyzer = "ik_smart", type = FieldType.Text)@JSONField(name = "abstract")
    private String abstracts;
}
