package jingzhou.MySQLTable;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity @Data
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectid;
    //完成人
    private String researcher;
    //第一完成单位
    private String institution;
    //关键词
    private String keywords;
    //中图分类号
    private String booktype;
    //学科分类号
    private String subjecttype;
    //成果简介
    private String briefintro;
    //成果类别
    private String type;
    //成果水平
    private String level;
    //研究起止时间
    private String startandfinishtime;
    //评价形式
    private String eveluatetype;
    //成果入库时间
    private String intime;
}
