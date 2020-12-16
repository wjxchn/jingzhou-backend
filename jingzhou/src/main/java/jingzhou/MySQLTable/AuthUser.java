package jingzhou.MySQLTable;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity @Data
public class AuthUser {
    //这里的username和user表中的username相同
    @Id
    private int authuserid;

    /*说明：原本只有username，现在用userid和user表关联，authuserid没有什么用，但是在数据库里当一个主键，实际开发中不会用到，也不需要set*/
    private int userid;
    private String username;
    private Integer institutionid;
    private String achievements;
    private String researchfield;
    private String realname;
    //nosql中的author id字段比较长
    private String authorid;


}
