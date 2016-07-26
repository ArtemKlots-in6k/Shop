package entity;

/**
 * Created by employee on 7/26/16.
 */
public class Top3 {
    private Item item;
    private long count;

    public Top3(Item item, long count) {
        this.item = item;
        this.count = count;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Top3{" +
                "item=" + item +
                ", count=" + count +
                '}';
    }


}
