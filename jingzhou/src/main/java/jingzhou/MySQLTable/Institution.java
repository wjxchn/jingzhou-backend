package jingzhou.MySQLTable;

import io.swagger.models.auth.In;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Institution {
    @Id
    Integer instituteid;
    String name;

    public Integer getInstituteid() {
        return instituteid;
    }

    public void setInstituteid(Integer instituteid) {
        this.instituteid = instituteid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
