package jingzhou.POJO;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@Document(indexName = "testdoct", indexStoreType = "paper")
public class Paper implements Serializable {
    @Id
    private ObjectId _id;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String id;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String title;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private ArrayList<Paper_Author>authors;

    private Paper_venue venue;

    private int year;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
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

    private ArrayList<String> url;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String pdf;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String abstracts;
}
