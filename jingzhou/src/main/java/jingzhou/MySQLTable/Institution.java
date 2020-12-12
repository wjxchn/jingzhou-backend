package jingzhou.MySQLTable;

import io.swagger.models.auth.In;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Institution {
    @Id
    Integer instituteid;

    String institutionname;

    public Integer getInstituteid() {
        return instituteid;
    }

    public void setInstituteid(Integer instituteid) {
        this.instituteid = instituteid;
    }

    public String getInstitutionname() {
        return institutionname;
    }

    public void setInstitutionname(String institutionname) {
        this.institutionname = institutionname;
    }

}
