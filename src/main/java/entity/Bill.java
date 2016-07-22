package entity;

import java.time.LocalDate;

/**
 * Created by Artem Klots on 7/22/16.
 */
public class Bill {
    private int id;
    private LocalDate date;
    private int userId;

    public Bill(LocalDate date, int userId) {
        this.date = date;
        this.userId = userId;
    }

    public Bill(int id, LocalDate date, int userId) {
        this(date, userId);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return id + " " + date + " " + userId;
    }
}
