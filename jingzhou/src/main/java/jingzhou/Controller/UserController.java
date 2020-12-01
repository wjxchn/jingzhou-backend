package jingzhou.Controller;

import jingzhou.MySQLTable.User;
import jingzhou.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("findusername/{id}")
    public String findUserById(@PathVariable("id") Integer id){
        return userRepository.findUserById((int)id).getTime().toString();
    }

}
