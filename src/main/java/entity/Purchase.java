package entity;

import java.math.BigDecimal;

/**
 * Created by Artem Klots on 7/22/16.
 */
public class Purchase {
    private int id;
    private int item_id;
    private BigDecimal price;
    private int bill_id;


    public Purchase(int item_id, BigDecimal price, int bill_id) {
        this.item_id = item_id;
        this.price = price;
        this.bill_id = bill_id;
    }

    public Purchase(int id, int item_id, BigDecimal price, int bill_id) {
        this(item_id, price, bill_id);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    @Override
    public String toString() {
        return id + " " + item_id + " " + price.toString() + " " + bill_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;

        return item_id == purchase.item_id
                && price.equals(purchase.price)
                && bill_id == purchase.bill_id;
    }
}