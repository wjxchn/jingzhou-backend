package jingzhou.POJO;


import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document(collection = "venue")
public class Venue {
    @MongoId
    ObjectId _id;

    @Field("venueid")
    String id;

    @Field("DisplayName")
    String displayname;

    @Field("NormalizedName")
    String normalname;
}
