package entity;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by employee on 7/26/16.
 */
public class UserBill {
    private int number;
    private Date date;
    private BigDecimal price;

    public UserBill(int number, Date date, BigDecimal price) {
        this.number = number;
        this.date = date;
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
