package algorithms.maze3D;

import algorithms.maze3D.Position3D;
import algorithms.search.AState;

public class Maze3DState extends AState {
    private final int rowIndex;
    private final int columnIndex;
    private final int depthIndex;


    public Maze3DState(int r,int c , int d) {
        super("(" + d + "," + c + "," + d + ")");
        this.columnIndex = c;
        this.rowIndex = r;
        this.depthIndex = d;
    }

    public Position3D getPosition() {
      return new Position3D(rowIndex,columnIndex,depthIndex);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Maze3DState maze3DState = (Maze3DState) obj;
        return getPosition().equals(maze3DState.getPosition());
    }

    @Override
    public int hashCode() {
        return getPosition().hashCode();
    }

    @Override
    public String toString() {
        return getPosition().toString();
    }
}