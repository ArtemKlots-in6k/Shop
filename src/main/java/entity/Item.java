package entity;

import java.math.BigDecimal;

/**
 * Created by Artem Klots on 7/21/16.
 */
public class Item {
    private int id;
    private String title;
    private int category_id;
    private BigDecimal price;

    public Item(String title, int category_id, BigDecimal price) {
        this.title = title;
        this.category_id = category_id;
        this.price = price;
    }

    public Item(int id, String title, int category_id, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.category_id = category_id;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return id + " " + title + " " + category_id + " " + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;

        return title.equals(item.title) && category_id == item.category_id && price.equals(item.price);
    }
}
