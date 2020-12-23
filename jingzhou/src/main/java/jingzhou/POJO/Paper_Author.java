package jingzhou.POJO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;


/*
*
* 这个类用于承接 nosql数据中paper数据的authors字段
* */

@Data
public class Paper_Author {

    @Field("name")@JsonProperty("name")
    private String name;

    @Field("id")@JsonProperty("id")
    private String id;

    @Field("org")@JsonProperty("org")
    private String affiliation;
}
