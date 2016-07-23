package entity;

import java.math.BigDecimal;

/**
 * Created by Artem Klots on 7/21/16.
 */
public class Item {
    private int id;
    private String title;
    private Category category;
    private BigDecimal price;

    public Item(String title, Category category, BigDecimal price) {
        this.title = title;
        this.category = category;
        this.price = price;
    }

    public Item(int id, String title, Category category, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return id + " " + title + " " + category + " " + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;

        return title.equals(item.title) && category.equals(item.category) && price.equals(item.price);
    }
}
