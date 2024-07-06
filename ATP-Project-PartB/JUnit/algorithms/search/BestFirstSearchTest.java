package algorithms.search;
import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for BestFirstSearch algorithm
 * Validates the behavior of the algorithm under different scenarios including handling of null inputs,
 * behavior in empty mazes, and mazes without defined start or goal positions.
 */
class BestFirstSearchTest {
    private BestFirstSearch bfs;

    /**
     * Initializes BestFirstSearch object before each test
     */
    @BeforeEach
    void setUp() {
        bfs = new BestFirstSearch();
    }

    /**
     *Tests the BestFirstSearch algorithm with null input to ensure it throws NullPointerException
     */
    @Test
    void testWithNullInput() {
        assertThrows(NullPointerException.class, () -> bfs.solve(null),
                "Should throw NullPointerException for null input.");
    }

    /**
     *Tests the BestFirstSearch algorithm in an empty maze (no walls) to check if it successfully finds a path
     */
    @Test
    void testWithEmptyMaze() {
        IMazeGenerator mg = new EmptyMazeGenerator();  // Using the EmptyMazeGenerator to create a walkable maze
        Maze maze = mg.generate(30, 30);  // Generate a 30x30 empty maze (no walls, all walkable)
        ISearchable emptyMaze = new SearchableMaze(maze);

        Solution solution = bfs.solve(emptyMaze);
        assertFalse(solution.getSolutionPath().isEmpty(),
                "Solution path should not be empty for an empty maze with no obstacles.");
    }

    /**
     *Tests the BestFirstSearch algorithm with a maze that has no defined start or goal positions to ensure it returns null
     */
    @Test
    void testWithNoStartOrGoal() {
        ISearchable emptyMaze = new SearchableMaze(new Maze(30, 30)); // Assuming Maze can handle zero-sized arrays
        Solution solution = bfs.solve(emptyMaze);
        assertNull(solution);
    }
}