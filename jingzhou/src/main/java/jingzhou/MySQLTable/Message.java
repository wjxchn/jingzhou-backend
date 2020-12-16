package jingzhou.MySQLTable;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity @Data
public class Message {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageid;
    private int senderid;
    private int receiverid;
    private String content;

}
