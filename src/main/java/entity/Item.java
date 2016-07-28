package entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Artem Klots on 7/21/16.
 */
@Entity
@Table(name = "items")
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

    public Item() {
    }

    @Column(unique = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "category_id")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(unique = true)
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

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }
}
