package algorithms.maze3D;

public class Position3D {
    private final int depthIndex;
    private final int rowIndex;
    private final int columnIndex;

    public Position3D(int rowIndex, int columnIndex, int depthIndex) {
        this.depthIndex = depthIndex;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public int getDepthIndex() {
        return depthIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position3D that = (Position3D) obj;
        return depthIndex == that.depthIndex && rowIndex == that.rowIndex && columnIndex == that.columnIndex;
    }

    @Override
    public int hashCode() {
        return 31 * depthIndex + 31 * rowIndex + 31 * columnIndex;
    }

    @Override
    public String toString() {
        return "{" + depthIndex + "," + rowIndex + "," + columnIndex + "}";
    }
}