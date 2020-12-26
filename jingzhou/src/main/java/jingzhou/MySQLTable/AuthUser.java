package jingzhou.MySQLTable;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "authuser")
@Data
public class AuthUser implements Serializable {
    //这里的username和user表中的username相同
    @Id
    private int authuserid;

    /*说明：原本只有username，现在用userid和user表关联，authuserid没有什么用，但是在数据库里当一个主键，实际开发中不会用到，也不需要set*/
    private int userid;
    private String username;
    private String institution;
    private String achievements;
    private String researchfield;
    private String realname;
    //nosql中的author id字段比较长
    private String authorid;

    private int fans;
    //个人简介
    private String intro;
    //组织
    private String organization;
    //发表论文数
    private int papers;
    //研究领域
    private String field;

}
