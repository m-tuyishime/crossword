package rows;
import Column;

public class Row {
    private Column[] content;
    private int index;
    private int size;

    public Row(int index, int size) {
        this.index = index;
        this.size = size;
    }

    private int getIndex() {
        return index;
    }

    private int getSize() {
        return size;
    }

    private Column[] getContent() {
        return content;
    }
}
