package entity;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by Artem Klots on 7/22/16.
 */
public class Bill {
    private int id;
    private Date date;
    private int userId;

    public Bill(Date date, int userId) {
        this.date = date;
        this.userId = userId;
    }

    public Bill(int id, Date date, int userId) {
        this(date, userId);
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

    @Override
    public String toString() {
        return id + " " + date + " " + userId;
    }
}
