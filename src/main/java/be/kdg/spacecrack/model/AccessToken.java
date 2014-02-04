package be.kdg.spacecrack.model;

import javax.persistence.*;

/**
 * Created by Tim on 3/02/14.
 */
@Entity
@Table(name = "T_AccessToken")
public class AccessToken {
    private int accessTokenId;

    private String value;

    private User user;

    public AccessToken() {
    }

    public AccessToken(String value) {
        this.value = value;
    }

    @Id
    @GeneratedValue
    public int getAccessTokenId() {
        return accessTokenId;
    }

    @Column
    public String getValue() {
        return value;
    }

    @OneToOne
    @PrimaryKeyJoinColumn
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAccessTokenId(int accessTokenId) {
        this.accessTokenId = accessTokenId;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
