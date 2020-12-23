package jingzhou.Service;

import jingzhou.MySQLTable.Institution;
import jingzhou.repository.InstitutionRankRepository;
import jingzhou.repository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionService {

    @Autowired
    InstitutionRepository institutionRepository;

    @Autowired
    InstitutionRankRepository institutionRankRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public Institution getByID(int institutionid){
        return institutionRepository.findInstitutionByInstitutionid(institutionid);
    }

    public Institution getByName(String institutionname){
        return institutionRepository.findInstitutionByInstitutionname(institutionname);
    }

    public List<Institution> getBykeyword(String institutionname){

        String institutionnames="^.*"+institutionname+".*$";
        System.out.println("service : get by institutionname");
            
        Criteria criteria = new Criteria();
        criteria.and("institutionname").regex(institutionnames);
            
        Query query = new Query();
        query.addCriteria(Criteria.where("institutionname").regex(institutionnames));
        List<Institution> Institutions = mongoTemplate.find(query, Institution.class);
    
        return Institutions;
    }
}
