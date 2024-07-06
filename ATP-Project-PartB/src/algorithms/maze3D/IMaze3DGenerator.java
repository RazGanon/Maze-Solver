package algorithms.maze3D;

/**
 * Interface for generating 3D mazes
 */
public interface IMaze3DGenerator {

    /**
     * Generates a 3D maze with the specified dimensions
     * @param depth the depth (z-dimension) of the 3D maze
     * @param row the number of rows (y-dimension) of the 3D maze
     * @param column the number of columns (x-dimension) of the 3D maze
     * @return a 3D maze with the specified dimensions
     */
    Maze3D generate(int depth, int row, int column);

    /**
     * Measures the time taken by the maze generation algorithm to generate a 3D maze
     * @param depth the depth (z-dimension) of the 3D maze
     * @param row the number of rows (y-dimension) of the 3D maze
     * @param column the number of columns (x-dimension) of the 3D maze
     * @return the time taken in milliseconds to generate the 3D maze
     */
    long measureAlgorithmTimeMillis(int depth, int row, int column);
}
