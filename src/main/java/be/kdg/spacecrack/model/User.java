package be.kdg.spacecrack.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Tim on 3/02/14.
 */
@Entity
@Table(name="T_User")
public class User implements Serializable{

    private int id;

    private String name;

    private String password;

    private AccessToken token;

    public User() {
        System.out.println("lol");
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    @Column
    public String getName() {
        return name;
    }

    @Column
    public String getPassword() {
        return password;
    }

    @OneToOne(mappedBy ="user", cascade = CascadeType.ALL)
    public AccessToken getToken() {
        return token;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(AccessToken token) {
        this.token = token;
    }
}
