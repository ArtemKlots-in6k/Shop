package entity;

/**
 * Created by Artem Klots on 7/21/16.
 */
public class Category {
    private int id;
    private String title;

    public Category(String title) {
        this.title = title;
    }

    public Category(int id, String title) {
        this.id = id;
        this.title = title;
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

    @Override
    public String toString() {
        return id + " " + title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;

        return title.equals(category.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
