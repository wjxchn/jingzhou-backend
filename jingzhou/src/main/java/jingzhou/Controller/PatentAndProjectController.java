package jingzhou.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jingzhou.MySQLTable.Patent;
import jingzhou.MySQLTable.Project;
import jingzhou.POJO.Result;
import jingzhou.repository.PatentRepository;
import jingzhou.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "专利和科研成果类")
@RestController
public class PatentAndProjectController {
    @Autowired
    PatentRepository patentRepository;

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("/api/getpatent/{id}")
    @ApiOperation(value = "通过id获取专利")
    private Result findPatentById(@PathVariable("id") int id){
        Result result = new Result("获取信息成功", 200);
        Patent patent = patentRepository.getPatentByPatentid(id);
        if (patent != null){
            result.getData().put("patent", patent);
            return result;
        }
        return new Result("获取信息失败", 400);
    }

    @GetMapping("/api/getproject/{id}")
    @ApiOperation(value = "通过id获取科研项目")
    private Result findProjectById(@PathVariable("id") int id){

        Result result = new Result("获取信息成功", 200);
        Project project = projectRepository.getProjectByProjectid(id);
        if (project != null){
            result.getData().put("project", project);
            return result;
        }
        return new Result("获取信息失败", 400);
    }

    @GetMapping("/api/searchpatent")
    @ApiOperation(value = "精确查找专利")
    private Result searchPatent(@RequestParam("name") String name){

        Result result = new Result("获取信息成功", 200);
        Patent patent = patentRepository.getPatentByPatentname(name);
        if (patent != null){
            result.getData().put("patent", patent);
            return result;
        }
        return new Result("获取信息失败", 400);
    }

    @GetMapping("/api/searchproject")
    @ApiOperation(value = "精确查找成果")
    private Result searchProject(@RequestParam("name") String name){

        Result result = new Result("获取信息成功", 200);
        Project project = projectRepository.getProjectByProjectname(name);
        if (project != null){
            result.getData().put("project", project);
            return result;
        }
        return new Result("获取信息失败", 400);
    }

    @GetMapping("/api/fuzzysearchpatent")
    @ApiOperation(value = "模糊查找专利")
    private Result fuzzySearchPatent(@RequestParam("keywords") String keywords){

        Result result = new Result("获取信息成功", 200);
        List<Patent> patents = patentRepository.getPatentsByPatentnameContains(keywords);
        if (patents != null){
            result.getData().put("patents", patents);
            return result;
        }
        return new Result("获取信息失败", 400);
    }

    @GetMapping("/api/fuzzysearchproject")
    @ApiOperation(value = "模糊查找成果")
    private Result fuzzySearchProject(@RequestParam("keywords") String keywords){

        Result result = new Result("获取信息成功", 200);
        List<Project> projects = projectRepository.getProjectsByProjectnameContains(keywords);
        if (projects != null){
            result.getData().put("projects", projects);
            return result;
        }
        return new Result("获取信息失败", 400);
    }
}
