package algorithms.maze3D;

/**
 * Abstract class that provides a template for generating 3D mazes.
 * Implements the IMaze3DGenerator interface.
 */
public abstract class AMaze3DGenerator implements IMaze3DGenerator {

    /**
     * Implements the method from IMaze3DGenerator.
     * @param depth the depth (z-dimension) of the 3D maze
     * @param row the number of rows (y-dimension) of the 3D maze
     * @param column the number of columns (x-dimension) of the 3D maze
     * @return the time taken in milliseconds to generate the 3D maze
     */
    @Override
    public long measureAlgorithmTimeMillis(int depth, int row, int column) {
        long startTime = System.currentTimeMillis();
        generate(depth, row, column);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
