package jingzhou.POJO;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;


/**
 * nosql的paper数据中的venue字段
 * */
@Data
public class Paper_venue {

    @Field("raw")@JSONField(name = "venue.raw")
    private String raw;

    @Field("id")@JSONField(name = "venue.id")
    private String id;

}
