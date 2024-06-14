package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.Objects;

public class MazeState extends AState {
    private int row;
    private int column;

    public MazeState(int row, int column) {
        super("(" + row + "," + column + ")");
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    public Position getPosition(){
        return new Position(getRow(),getColumn());
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MazeState mazeState = (MazeState) obj;
        return row == mazeState.row && column == mazeState.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }}