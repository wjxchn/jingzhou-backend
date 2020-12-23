package jingzhou.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Tag {

    //研究方向
    @JsonProperty("t")
    private String t;

    //专业权重
    @JsonProperty("w")
    private int w;
}
