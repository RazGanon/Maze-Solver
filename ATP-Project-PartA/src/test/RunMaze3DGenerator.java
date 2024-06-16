package test;
import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;

public class RunMaze3DGenerator {
    public static void main(String[] args) {
        IMaze3DGenerator mg = new MyMaze3DGenerator();
        long start = System.currentTimeMillis();
        Maze3D maze = mg.generate(30, 30, 30);
        long end = System.currentTimeMillis();
        System.out.println("3D Maze generation time: " + (end - start) + " ms");
        // Additional checks to ensure the maze is solvable can be added here
    }
}