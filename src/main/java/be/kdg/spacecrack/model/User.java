package be.kdg.spacecrack.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Tim on 3/02/14.
 */
@Entity
@Table(name="T_User")
public class User implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String name;
    @Column
    private String password;

    public User() {
        System.out.println("lol");
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
