public class DiagonalRow extends Row {
    public DiagonalRow(int index, int size, int orientation) {
        super(index, size);

        switch (orientation) {
            case 0:
                for (int i = 0; i < size; i++) {
                    int x;
                    int y;
                    if (index > size) {
                        x = Table.getTableWidth() - size + i;
                        y = Table.getTableHeigth() - 1 - i;
                    } else {
                        x = i;
                        y = getSize() - 1 - i;
                    }
                    Coordinates coordinates = new Coordinates(x, y);
                    Column column = Column.getInstance(coordinates);
                    setContent(i, column);
                }
                ;
                break;
            case 1:
                int i = Table.getTableWidth() - 1;
                for (int o = 0; o < size; o++) {
                    int x;
                    int y;
                    if (index > size) {
                        x = o;
                        y = size - 1 - o;
                    } else {
                        x = Table.getTableWidth() - 1 - index + o;
                        y = i;
                    }
                    Coordinates coordinates = new Coordinates(x, y);
                    Column column = Column.getInstance(coordinates);
                    setContent(Table.getTableWidth() - 1 - i, column);
                    i--;
                }
                ;
                break;
            default:
                throw new IllegalArgumentException(
                        "the diagonal row orientation must be either 0 (start from top left) or 1 (start from top right)");
        }
    }
}
