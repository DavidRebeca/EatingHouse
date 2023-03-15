package Business;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private int idUser;
    private String userName;
    private String password;
    private String type;

    public User(int idUser, String userName, String password, String type) {
        this.idUser = idUser;
        this.userName = userName;
        this.password = password;
        this.type = type;
    }
    public User(){}

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
