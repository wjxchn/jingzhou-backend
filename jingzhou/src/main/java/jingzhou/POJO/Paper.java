package jingzhou.POJO;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@Document(collection = "paper")
public class Paper implements Serializable {
    @MongoId
    private ObjectId _id;

    @Field("id")
    private String id;

    @Field("title")
    private String title;

    @Field("authors")
    private ArrayList<Paper_Author>authors;

    private Paper_venue venue;

    private int year;

    ArrayList<String> keywords;

    private int n_citation;

    private String page_start;

    private String page_end;

    private String lang;

    private String volume;

    private String issue;

    private String doi;

    private String isbn;

    private ArrayList<String> url;

}
