package jingzhou.POJO;


import lombok.Data;
import org.bson.types.ObjectId;
import org.hibernate.annotations.FetchProfile;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document(collection = "venue")
public class Venue {
    @Id
    ObjectId _id;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    String id;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    String displayname;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    String normalname;
}
