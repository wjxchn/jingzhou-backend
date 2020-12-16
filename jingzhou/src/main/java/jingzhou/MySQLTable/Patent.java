package jingzhou.MySQLTable;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity @Data
public class Patent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer patentid;
    String inventor;
    Date pubdate;
    String patentname;
    String pubnumber;
    String patentnumber;
    String applicant;
    String address;
    String classification;
    String abstractcontent;
    Date appdate;


}
