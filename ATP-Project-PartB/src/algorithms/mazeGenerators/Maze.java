package algorithms.mazeGenerators;

import java.io.Serializable;

public class Maze implements Serializable {
    Position start;
    Position goal;
    private int[][] matrix;

    /**
     * Constructs a Maze with the specified number of rows and columns.
     *
     * @param row    the number of rows in the maze
     * @param column the number of columns in the maze
     * @throws IllegalArgumentException if the number of rows or columns is less than 2
     */
    public Maze(int row, int column) {
        if (row < 2 || column < 2)
            throw new IllegalArgumentException("Invalid Maze Dimensions input");
        matrix = new int[row][column];
    }

    /**
     * Constructs a Maze from a byte array representation.
     *
     * @param list the byte array representing the maze
     */
    public Maze(byte[] list) {
        int index = 0;
        byte counter = list[0];
        String x = "";
        String y = "";

        // Parse row and column dimensions
        while (list[index] != -1) {
            x += list[index];
            index++;
        }
        index++;
        while (list[index] != -1) {
            y += list[index];
            index++;
        }
        index++;
        int row = Integer.parseInt(x), column = Integer.parseInt(y);
        matrix = new int[row][column];
        x = "";
        y = "";

        // Parse entrance position
        while (list[index] != -1) {
            x += list[index];
            index++;
        }
        index++;
        while (list[index] != -1) {
            y += list[index];
            index++;
        }
        index++;
        start = new Position(Integer.parseInt(x), Integer.parseInt(y));
        x = "";
        y = "";

        // Parse exit position
        while (list[index] != -1) {
            x += list[index];
            index++;
        }
        index++;
        while (list[index] != -2) {
            y += list[index];
            index++;
        }
        index++;
        goal = new Position(Integer.parseInt(x), Integer.parseInt(y));

        // Parse maze matrix
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                matrix[i][j] = list[index];
                index++;
            }
        }
    }

    /**
     * Returns the 2D array representing the maze.
     *
     * @return the maze matrix
     */
    public int[][] getMatrix() {
        return matrix;
    }

    /**
     * Returns the entrance position of the maze.
     *
     * @return the entrance position
     */
    public Position getStartPosition() {
        return start;
    }

    /**
     * Returns the exit position of the maze.
     *
     * @return the exit position
     */
    public Position getGoalPosition() {
        return goal;
    }

    /**
     * Prints the 2D maze to the console, marking the entrance with 'S' and the exit with 'E'.
     */
    public void print() {
        int[][] temp = matrix.clone();
        temp[start.getRowIndex()][start.getColumnIndex()] = 2;
        temp[goal.getRowIndex()][goal.getColumnIndex()] = 3;
        String body = "";
        for (int[] r : matrix) {
            body += "{";
            for (int i : r) {
                body += " ";
                if (i != 2 && i != 3)
                    body += i;
                else if (i == 2) {
                    body += "S";
                } else {
                    temp[goal.getRowIndex()][goal.getColumnIndex()] = 0;
                    body += "E";
                }
            }
            body += " }";
            body += "\n";
        }
        System.out.println(body);
    }

    /**
     * Helper method to add string representations of numbers to a byte array.
     *
     * @param num    the number as a string
     * @param list   the byte array to add the number to
     * @param counter the current index in the byte array
     */
    private void addUp(String num, byte[] list, int counter) {
        for (int i = 0; i < num.length(); i++) {
            list[counter + i] = (byte) (num.charAt(i) - '0');
        }
    }

    /**
     * Converts the maze to a byte array representation.
     *
     * @return the byte array representing the maze
     */
    public byte[] toByteArray() {
        String entranceX = String.valueOf(start.x);
        String entranceY = String.valueOf(start.y);
        String exitX = String.valueOf(goal.x);
        String exitY = String.valueOf(goal.y);
        String row = String.valueOf(matrix.length);
        String column = String.valueOf(matrix[0].length);
        byte[] list = new byte[matrix.length * matrix[0].length + entranceX.length() + entranceY.length() + exitX.length() + exitY.length() + row.length() + column.length() + 6];
        int counter = 0;

        addUp(row, list, counter);
        counter += row.length();
        list[counter] = -1;
        counter++;

        addUp(column, list, counter);
        counter += column.length();
        list[counter] = -1;
        counter++;

        addUp(entranceX, list, counter);
        counter += entranceX.length();
        list[counter] = -1;
        counter++;

        addUp(entranceY, list, counter);
        counter += entranceY.length();
        list[counter] = -1;
        counter++;

        addUp(exitX, list, counter);
        counter += exitX.length();
        list[counter] = -1;
        counter++;

        addUp(exitY, list, counter);
        counter += exitY.length();
        list[counter] = -2;
        counter++;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                list[counter] = (byte) matrix[i][j];
                counter++;
            }
        }
        return list;
    }


    // Methods for checking possible neighbors in the maze
    public boolean checkUp(Position s) {
        return s.getRowIndex() != 0 && matrix[s.getRowIndex() - 1][s.getColumnIndex()] == 0;
    }

    public boolean checkDown(Position s) {
        return s.getRowIndex() != matrix.length - 1 && matrix[s.getRowIndex() + 1][s.getColumnIndex()] == 0;
    }

    public boolean checkLeft(Position s) {
        return s.getColumnIndex() != 0 && matrix[s.getRowIndex()][s.getColumnIndex() - 1] == 0;
    }

    public boolean checkRight(Position s) {
        return s.getColumnIndex() != matrix[0].length - 1 && matrix[s.getRowIndex()][s.getColumnIndex() + 1] == 0;
    }

    public boolean checkUpLeft(Position s) {
        return (checkUp(s) || checkLeft(s)) && s.getRowIndex() != 0 && s.getColumnIndex() != 0 && matrix[s.getRowIndex() - 1][s.getColumnIndex() - 1] == 0;
    }

    public boolean checkDownLeft(Position s) {
        return (checkDown(s) || checkLeft(s)) && s.getRowIndex() != matrix.length - 1 && s.getColumnIndex() != 0 && matrix[s.getRowIndex() + 1][s.getColumnIndex() - 1] == 0;
    }

    public boolean checkUpRight(Position s) {
        return (checkUp(s) || checkRight(s)) && s.getRowIndex() != 0 && s.getColumnIndex() != matrix[0].length - 1 && matrix[s.getRowIndex() - 1][s.getColumnIndex() + 1] == 0;
    }

    public boolean checkDownRight(Position s) {
        return (checkDown(s) || checkRight(s)) && s.getRowIndex() != matrix.length - 1 && s.getColumnIndex() != matrix[0].length - 1 && matrix[s.getRowIndex() + 1][s.getColumnIndex() + 1] == 0;
    }
}
