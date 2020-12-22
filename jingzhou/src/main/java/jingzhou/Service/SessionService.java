package jingzhou.Service;

import jingzhou.MySQLTable.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class SessionService {

    private static final String get_Current_User = "current_user";
    private static final String get_Current_Email_Code = "current_email_code";

    public User getCurrentUser(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null){
            return (User) session.getAttribute(get_Current_User);
        }
        else {
            return null;
        }
    }

    public void setCurrentUser(HttpServletRequest request, User user){
        HttpSession session = request.getSession();
        session.setAttribute(get_Current_User, user);
    }

    public void removeCurrentUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute(get_Current_User);
    }

    public boolean isLoggedIn(HttpServletRequest request){
        return getCurrentUser(request) != null;
    }

    public void setCurrentEmailVerificationCode(HttpServletRequest request, String code) {
        HttpSession session = request.getSession();
        session.setAttribute(get_Current_Email_Code, code);
    }

    public boolean verifyCode(HttpServletRequest request, String code) {
        HttpSession session = request.getSession(false);
        if (session == null)
            return false;
        String oldCode = (String) session.getAttribute(get_Current_Email_Code);
        //System.out.println(oldCode);
        return code.equals(oldCode);
    }
}
