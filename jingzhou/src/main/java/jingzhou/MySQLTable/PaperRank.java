package jingzhou.MySQLTable;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity @Data
public class PaperRank {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rankid;
    private int citation;
    private String paperid;
    private String authorid;
    private String papername;
    private String field;

}
