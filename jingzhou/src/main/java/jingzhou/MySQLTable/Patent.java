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
    //专利名称
    String patentname;
    //专利类型
    String type;
    //申请专利号
    String patentnumber;
    //申请日
    Date appdate;
    //授权公布号
    String pubnumber;
    //授权公布日
    Date pubdate;
    //申请人
    String applicant;
    //地址
    String address;
    //发明人
    String inventor;
    //分类号
    String classification;
    //主分类号
    String mainclassification;
    //国省代码
    String provincecode;
    //代理机构
    String agentinstitution;
    //代理人
    String agentpeople;
    //主权项
    String force;
    //摘要
    String abstractcontent;
}
