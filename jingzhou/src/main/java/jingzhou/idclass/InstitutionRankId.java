package jingzhou.idclass;

import java.io.Serializable;

public class InstitutionRankId implements Serializable {
    private int institution;
    private String type;

    public int getInstitution() {
        return institution;
    }

    public void setInstitution(int institution) {
        this.institution = institution;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
