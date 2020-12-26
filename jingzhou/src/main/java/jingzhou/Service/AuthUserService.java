package jingzhou.Service;

import jingzhou.MySQLTable.AuthUser;
import jingzhou.repository.AuthUserRepository;
import jingzhou.repository.UserRepository;
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
    public AuthUser getAuthUserByRealname(String name){
        return authUserRepository.findByRealname(name);
    }

    public void insertAuthUser(AuthUser user){
        authUserRepository.save(user);
    }

    public void updateAuthUser(AuthUser user){
        authUserRepository.saveAndFlush(user);
    }
}
