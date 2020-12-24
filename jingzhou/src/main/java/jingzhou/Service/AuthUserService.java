package jingzhou.Service;

import jingzhou.MySQLTable.AuthUser;
import jingzhou.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService {
    @Autowired
    AuthUserRepository authUserRepository;

    public AuthUser getAuthUserByUserID(int userid){
        return authUserRepository.findByUserid(userid);
    }

    public AuthUser getAuthUserByUsername(String username){
        return authUserRepository.findAuthUserByUsername(username);
    }

    public void insertAuthUser(AuthUser user){
        userRepository.save(user);
    }

    public void updateAuthUser(AuthUser user){
        userRepository.saveAndFlush(user);
    }
}
