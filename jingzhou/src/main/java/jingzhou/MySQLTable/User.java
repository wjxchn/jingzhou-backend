package jingzhou.MySQLTable;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class User implements Serializable {

    @Id
    private int userid;
    //用户名
    private String username;
    //密码
    private String password;
    //是否为认证用户，0无权限，1有权限
    private int isauth;
    //是否有下载权限，0无权限，1有权限
    private int downloadauth;
    //邮箱
    private String email;
    //注册时间
    private Date time;

    private String phone;

    private String pic;

    private String intro;

}
