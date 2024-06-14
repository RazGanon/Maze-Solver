package algorithms.search;

import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    private BestFirstSearch bfs;

    @BeforeEach
    void setUp() {
        bfs = new BestFirstSearch();
    }

    @Test
    void testWithNullInput() {
        assertThrows(NullPointerException.class, () -> bfs.solve(null),
                "Should throw NullPointerException for null input.");
    }

    @Test
    void testWithEmptyMaze() {
        // Assuming Maze is initialized correctly and can handle setting all cells as walkable
        IMazeGenerator mg = new EmptyMazeGenerator();  // Using the EmptyMazeGenerator to create a walkable maze
        Maze maze = mg.generate(30, 30);  // Generate a 30x30 empty maze (no walls, all walkable)
        ISearchable emptyMaze = new SearchableMaze(maze);

        Solution solution = bfs.solve(emptyMaze);
        assertFalse(solution.getSolutionPath().isEmpty(),
                "Solution path should not be empty for an empty maze with no obstacles.");
    }

    @Test
    void testWithNoStartOrGoal() {
        ISearchable emptyMaze = new SearchableMaze(new Maze(30, 30)); // Assuming Maze can handle zero-sized arrays
        Solution solution = bfs.solve(emptyMaze);
        assertNull(solution);
    }
}