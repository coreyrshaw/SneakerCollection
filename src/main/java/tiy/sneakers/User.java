package tiy.sneakers;

import javax.persistence.*;

/**
 * Created by Sulton on 5/22/2016.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    public int id;

    @Column(nullable = false, unique = true)
    public String name;

    @Column(nullable = false)
    public String password;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
