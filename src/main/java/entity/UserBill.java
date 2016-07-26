package entity;

import java.math.BigDecimal;

/**
 * Created by employee on 7/26/16.
 */
public class UserBill {
    private Bill bill;
    private BigDecimal price;

    public UserBill(Bill bill, BigDecimal price) {
        this.bill = bill;
        this.price = price;
    }


    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "UserBill{" +
                "bill=" + bill +
                ", price=" + price +
                '}';
    }
}
