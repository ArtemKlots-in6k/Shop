package entity;

import java.math.BigDecimal;

/**
 * Created by Artem Klots on 7/22/16.
 */
public class Purchase {
    private int id;
    private Item item;
    private BigDecimal price;
    private Bill bill;


    public Purchase(Item item, BigDecimal price, Bill bill) {
        this.item = item;
        this.price = price;
        this.bill = bill;
    }

    public Purchase(int id, Item item, BigDecimal price, Bill bill) {
        this(item, price, bill);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    @Override
    public String toString() {
        return id + " " + item + " " + price.toString() + " " + bill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;

        return item.equals(purchase.item)
                && price.equals(purchase.price)
                && bill == purchase.bill;
    }
}
