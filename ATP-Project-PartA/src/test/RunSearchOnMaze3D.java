package test;

import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.SearchableMaze3D;
import algorithms.search.*;

import java.util.ArrayList;

public class RunSearchOnMaze3D {
    public static void main(String[] args) {
        IMaze3DGenerator mg = new MyMaze3DGenerator();
        Maze3D maze = mg.generate(30, 30, 30);
        SearchableMaze3D searchableMaze = new SearchableMaze3D(maze);

        solveProblem(searchableMaze, new BreadthFirstSearch());
        solveProblem(searchableMaze, new DepthFirstSearch());
        solveProblem(searchableMaze, new BestFirstSearch());
    }

    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        long startTime = System.currentTimeMillis(); // Start timing
        Solution solution = searcher.solve(domain);
        long endTime = System.currentTimeMillis(); // End timing

        if (solution == null || solution.getSolutionPath() == null) {
            System.out.println(searcher.getName() + " did not find a solution.");
            return;
        }

        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        System.out.println(String.format("Time taken: %d ms", (endTime - startTime)));

        // Printing Solution path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s", i, solutionPath.get(i)));
        }
    }

}