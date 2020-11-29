package jingzhou.POJO;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/*
*
* 这个类用于承接Author数据中的 论文-作者顺序
* */
@Data
public class pubs implements Serializable {

    //论文id
    @Field("i")
    private String paperid;
    //0作，1作，2作
    @Field("r")
    private int order;
}
