package jingzhou.MySQLTable;

import io.swagger.models.auth.In;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity @Data
public class Institution {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer institutionid;

    String institutionname;

}
