package jingzhou.Service;

import jingzhou.MySQLTable.Institution;
import jingzhou.repository.InstitutionRankRepository;
import jingzhou.repository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstitutionService {

    @Autowired
    InstitutionRepository institutionRepository;

    @Autowired
    InstitutionRankRepository institutionRankRepository;

    public Institution getByID(int institutionid){
        return institutionRepository.findInstitutionByInstitutionid(institutionid);
    }

    public Institution getByName(String institutionname){
        return institutionRepository.findInstitutionByInstitutionname(institutionname);
    }
}
