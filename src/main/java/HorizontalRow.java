public class HorizontalRow extends Row {
    public HorizontalRow(int index, int size) {
        super(index, size);
        for (int i = 0; i < size; i++) {
            int x = i;
            int y = index;

            Coordinates coordinates = new Coordinates(x, y);
            Column column = Column.getInstance(coordinates);
            setContent(i, column);
        }
    }

    public void print() {
        System.out.print("| ");
        for (int i = 0; i < getSize(); i++) {
            System.out.print(getContent(i).getContent());
            System.out.print("  ");
        }
        System.out.print("|\n");
    }
}
