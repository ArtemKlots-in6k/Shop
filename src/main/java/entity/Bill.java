package entity;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by Artem Klots on 7/22/16.
 */
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

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

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
}
