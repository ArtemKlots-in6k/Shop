package entity;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by Artem Klots on 7/22/16.
 */
@Entity
@Table(name = "bills")
public class Bill {
    private int id;
    private Date date;
    private User user;

    public Bill(Date date, User user) {
        this.date = date;
        this.user = user;
    }

    public Bill(int id, Date date, User user) {
        this(date, user);
        this.id = id;
    }

    public Bill() {
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
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "id: " + id + "; date: " + date + "; user name:" + user.getName() + "; user id:" + user.getId();
    }

    @Override
    public boolean equals(Object obj) {
        Bill bill = (Bill) obj;
        return date.equals(((Bill) obj).date) && user.equals(((Bill) obj).getUser());
    }
}
