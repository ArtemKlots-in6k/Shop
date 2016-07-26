package entity;

/**
 * Created by employee on 7/26/16.
 */
public class CategoryStatistic {
    private int id;
    private String title;
    private long sum;

    public CategoryStatistic(int id, String title, long sum) {
        this.id = id;
        this.title = title;
        this.sum = sum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "CategoryStatistic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", sum=" + sum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryStatistic that = (CategoryStatistic) o;

        if (sum != that.sum) return false;
        return title != null ? title.equals(that.title) : that.title == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (int) (sum ^ (sum >>> 32));
        return result;
    }
}
