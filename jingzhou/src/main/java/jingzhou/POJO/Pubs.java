package jingzhou.POJO;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/*
 * 这个类用于承接Author数据中的 论文-作者顺序
 *
 */
@Data
public class Pubs implements Serializable {

    /**
     * @param i 是paperid
     * @param r 是order
     */

    //论文id
    @Field(name = "i")
    private String i;//paperid
    //0作，1作，2作
    @Field(name = "r")
    private int r;//order
}
