public class VerticalRow extends Row {
    public VerticalRow(int index, int size) {
        super(index, size);
        for (int i = 0; i < size; i++) {
            int x = index;
            int y = i;

            Coordinates coordinates = new Coordinates(x, y);
            Column column = Column.getInstance(coordinates);
            setContent(i, column);
        }
    }
}
