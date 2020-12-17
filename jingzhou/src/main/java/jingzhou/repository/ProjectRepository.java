package jingzhou.repository;

import jingzhou.MySQLTable.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Project getProjectByProjectid(Integer projectid);
}
