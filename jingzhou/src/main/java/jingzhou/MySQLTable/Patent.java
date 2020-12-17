package jingzhou.MySQLTable;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity @Data
public class Patent {
    //专利ID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer patentid;
    //发明人
    String inventor;
    //申请公布日
    Date pubdate;
    //专利名称
    String patentname;
    //申请公布号
    String pubnumber;
    //申请号
    String patentnumber;
    //申请人
    String applicant;
    //地址
    String address;
    //分类号
    String classification;
    //摘要
    String abstractcontent;
    //申请日
    Date appdate;


}
