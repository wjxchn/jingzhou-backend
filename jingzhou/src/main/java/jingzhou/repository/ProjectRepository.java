package jingzhou.repository;

import jingzhou.MySQLTable.Project;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Project getProjectByProjectid(Integer projectid);
    Project getProjectByProjectname(String projectname);
    List<Project> getProjectsByProjectnameContains(String projectname, Pageable pageable);
}
