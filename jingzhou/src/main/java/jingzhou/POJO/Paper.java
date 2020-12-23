package jingzhou.POJO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;

@Data
@Document(indexName = "jingzhou.paper")
public class Paper implements Serializable {
    @Id @JsonProperty("id")
    private String _id;

    @Field(name = "paperid", analyzer = "ik_smart", type = FieldType.Text)
    @JsonProperty("paperid")
    private String paperid;

    @Field(analyzer = "ik_smart", type = FieldType.Text)@JsonProperty("title")
    private String title;

    @Field(name = "authors", analyzer = "ik_smart", type = FieldType.Nested)@JsonProperty("authors")
    private ArrayList<Paper_Author>authors;
    @JsonProperty("venue")
    private Paper_venue venue;

    @JsonProperty("year")
    private int year;

    @Field(analyzer = "ik_smart", type = FieldType.Nested)@JsonProperty("keywords")
    ArrayList<String> keywords;

    @JsonProperty("n_citation")
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

    @Field(type = FieldType.Keyword)
    private String issn;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String isbn;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String doi;

    @Field(name = "url", analyzer = "ik_smart", type = FieldType.Nested)
    private ArrayList<String> url;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String pdf;

    @Field(name = "abstract", analyzer = "ik_smart", type = FieldType.Text)@JsonProperty("abstract")
    private String abstracts;
}
