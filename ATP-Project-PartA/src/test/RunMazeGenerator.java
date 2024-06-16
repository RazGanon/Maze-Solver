package test;
import algorithms.mazeGenerators.*;
public class RunMazeGenerator {
    public static void main(String[] args) {
        testMazeGenerator(new EmptyMazeGenerator());
        testMazeGenerator(new SimpleMazeGenerator());
        testMazeGenerator(new MyMazeGenerator());
    }
    private static void testMazeGenerator(IMazeGenerator mazeGenerator) {
// prints the time it takes the algorithm to run
        System.out.println(String.format("Maze generationtime(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(100,100)));
// generate another maz
        Maze maze = mazeGenerator.generate(100, 100);
// prints the maze
        maze.printMaze();
// get the maze entrance
        Position startPosition = maze.getStart();
// print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
// prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getEnd()));
    }
}