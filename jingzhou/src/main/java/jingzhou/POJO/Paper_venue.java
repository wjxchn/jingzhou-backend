package jingzhou.POJO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;


/**
 * nosql的paper数据中的venue字段
 * */
@Data
public class Paper_venue {

    @Field("raw")@JsonProperty("raw")
    private String raw;

    @Field("id")@JsonProperty("id")
    private String id;

}
