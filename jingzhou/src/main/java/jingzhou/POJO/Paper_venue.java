package jingzhou.POJO;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;


/**
 * nosql的paper数据中的venue字段
 * */
@Data
public class Paper_venue {

    @Field("raw")
    private String raw;

    @Field("id")
    private String id;

}
