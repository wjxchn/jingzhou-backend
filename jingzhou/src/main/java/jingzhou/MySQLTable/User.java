package jingzhou.MySQLTable;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class User {
    @Id
    private int id;
    private String password;
    private int isauth;
    private int downloadauth;
    private String username;
    private String email;
    private Date time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
