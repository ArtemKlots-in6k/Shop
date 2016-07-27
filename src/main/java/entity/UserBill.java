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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserBill userBill = (UserBill) o;

        if (bill != null ? !bill.equals(userBill.bill) : userBill.bill != null) return false;
        return price != null ? price.equals(userBill.price) : userBill.price == null;

    }

    @Override
    public int hashCode() {
        int result = bill != null ? bill.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
