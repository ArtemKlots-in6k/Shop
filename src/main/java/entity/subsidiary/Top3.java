package entity.subsidiary;

import entity.Item;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Top3 top3 = (Top3) o;

        if (count != top3.count) return false;
        return item != null ? item.equals(top3.item) : top3.item == null;

    }
}
