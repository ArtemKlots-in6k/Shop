package entity;

import javax.persistence.*;

/**
 * Created by Artem Klots on 7/22/16.
 */
@Entity
@Table(name = "users")
public class User {
    private int id;
    private String name;

    public User(String name) {
        this.name = name;
    }

    public User(int id, String name) {
        this(name);
        this.id = id;
    }

    public User() {
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;

        return name.equals(user.name);
    }
}
