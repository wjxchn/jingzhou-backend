package jingzhou.POJO;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;


@Data
public class Result implements Serializable {
    private HashMap<String, Object> data;
    private String msg;
    private int code;

    public Result(String msg, int code){
        this.msg=msg;
        this.code=code;
        this.data = new HashMap<String, Object>();
    }

    public Result(){}
}
