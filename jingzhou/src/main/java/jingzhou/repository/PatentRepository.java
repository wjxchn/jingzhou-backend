package jingzhou.repository;

import jingzhou.MySQLTable.Patent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatentRepository extends JpaRepository<Patent, Integer>{
}
