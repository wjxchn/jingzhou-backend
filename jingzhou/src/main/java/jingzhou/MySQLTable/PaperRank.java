package jingzhou.MySQLTable;

import lombok.Data;

import javax.persistence.*;

@Entity @Data
public class PaperRank {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rankid;
    private int amount;
    private String paperid;
    private String authorid;
    private String papername;
    private String field;

}
