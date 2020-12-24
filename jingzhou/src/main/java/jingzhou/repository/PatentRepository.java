package jingzhou.repository;

import jingzhou.MySQLTable.Patent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatentRepository extends JpaRepository<Patent, Integer>{
    Patent getPatentByPatentid(Integer patentid);
    Patent getPatentByPatentname(String name);
    List<Patent> getPatentsByPatentnameContains(String name, Pageable pageable);
}
