package jingzhou.MySQLTable;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class User {
    //用户名
    @Id
    private String username;
    //密码
    private String password;
    //是否为认证用户，0无权限，1有权限
    private int isauth;
    //是否有下载权限，0无权限，1有权限
    private int downloadauth;
    //邮箱
    private String email;
    //注册时间
    private Date time;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsauth() {
        return isauth;
    }

    public void setIsauth(int isauth) {
        this.isauth = isauth;
    }

    public int getDownloadauth() {
        return downloadauth;
    }

    public void setDownloadauth(int downloadauth) {
        this.downloadauth = downloadauth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
