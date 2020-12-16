package jingzhou.MySQLTable;

import lombok.Data;

import javax.persistence.*;

@Entity @Data
public class InstitutionRank {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rankid;
    private Integer institutionid;
    private String type;
    int amount;

}
