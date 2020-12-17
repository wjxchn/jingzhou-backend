package jingzhou.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jingzhou.MySQLTable.Patent;
import jingzhou.MySQLTable.Project;
import jingzhou.repository.PatentRepository;
import jingzhou.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Api(value = "专利和科研成果类")
@RestController
public class PatentAndProjectController {
    @Autowired
    PatentRepository patentRepository;

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("/api/getpatent/{id}")
    @ApiOperation(value = "通过id获取专利")
    private Patent findPatentById(@PathVariable("id") int id){
        Patent patent = patentRepository.getPatentByPatentid(id);
        return patent;
    }

    @GetMapping("/api/getproject/{id}")
    @ApiOperation(value = "通过id获取科研项目")
    private Project findProjectById(@PathVariable("id") int id){
        Project project = projectRepository.getProjectByProjectid(id);
        return project;
    }

    @GetMapping("/api/searchpatent")
    @ApiOperation(value = "精确查找专利")
    private Patent searchPatent(@RequestParam("name") String name){
        Patent patent = patentRepository.getPatentByPatentname(name);
        return patent;
    }

    @GetMapping("/api/searchproject")
    @ApiOperation(value = "精确查找成果")
    private Project searchProject(@RequestParam("name") String name){
        Project project = projectRepository.getProjectByProjectname(name);
        return project;
    }

    @GetMapping("/api/fuzzysearchpatent")
    @ApiOperation(value = "模糊查找专利")
    private List<Patent> fuzzySearchPatent(@RequestParam("keywords") String keywords){
        List<Patent> patents = patentRepository.getPatentsByPatentnameContains(keywords);
        return patents;
    }

    @GetMapping("/api/fuzzysearchproject")
    @ApiOperation(value = "模糊查找成果")
    private List<Project> fuzzySearchProject(@RequestParam("keywords") String keywords){
        List<Project> projects = projectRepository.getProjectsByProjectnameContains(keywords);
        return projects;
    }
}
