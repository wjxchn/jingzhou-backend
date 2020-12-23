package jingzhou.POJO;


import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Column;

@Data
@Document(indexName = "jingzhou.venue")
public class Venue {
    @Id
    ObjectId _id;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    @Column(name = "venueid")
    String venueid;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    String displayname;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    String normalname;
}
