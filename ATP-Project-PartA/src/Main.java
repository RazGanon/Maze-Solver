
import algorithms.mazeGenerators.*;


public class Main {
    public static void main(String[] args) {
        IMazeGenerator emptyMazeGenerator = new EmptyMazeGenerator();
        IMazeGenerator simpleMazeGenerator = new SimpleMazeGenerator();
        IMazeGenerator myMazeGenerator = new MyMazeGenerator();

        int rows = 10;
        int cols = 10;

//        System.out.println("Empty Maze:");
//        Maze emptyMaze = emptyMazeGenerator.generate(rows, cols);
//        System.out.println(emptyMaze);
//
//        System.out.println("Simple Maze:");
//        Maze simpleMaze = simpleMazeGenerator.generate(rows, cols);
//        System.out.println(simpleMaze);
//
//        System.out.println("My Maze (using Prim's algorithm):");
        Maze myMaze = myMazeGenerator.generate(rows, cols);
        myMaze.printMaze();

        //System.out.println("Time taken to generate Empty Maze: " + emptyMazeGenerator.measureAlgorithmTimeMillis(rows, cols) + " ms");
        //System.out.println("Time taken to generate Simple Maze: " + simpleMazeGenerator.measureAlgorithmTimeMillis(rows, cols) + " ms");
        System.out.println("Time taken to generate My Maze: " + myMazeGenerator.measureAlgorithmTimeMillis(rows, cols) +"ms");
    }


}