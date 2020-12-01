package jingzhou.MySQLTable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Institution {
    @Id
    int instituteid;
    String name;

    public int getInstituteid() {
        return instituteid;
    }

    public void setInstituteid(int instituteid) {
        this.instituteid = instituteid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
