package jingzhou.Service;

import jingzhou.MySQLTable.User;
import jingzhou.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User login(String username, String password){
        return userRepository.findUserByUsernameAndPassword(username,password);
    }

    public User getUserByName(String username){
        return userRepository.findUserByUsername(username);
    }

    public User getUserById(int useid){
        return userRepository.findUserByUserid(useid);
    }

    public void insertUser(User user){
        userRepository.save(user);
    }

    public void updateUser(User user){
        userRepository.saveAndFlush(user);
    }


}
