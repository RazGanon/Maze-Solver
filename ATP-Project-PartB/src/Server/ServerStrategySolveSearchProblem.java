package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServerStrategySolveSearchProblem implements IServerStrategy {

    /**
     * Server strategy that receives a maze from the client, solves it, and returns the solution
     * If the maze has been solved before, retrieves the solution from saved files
     * @param inFromClient the input stream from the client containing the maze
     * @param outToClient  the output stream to the client where the solution will be sent
     */
    @Override
    public void handleClient(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Maze maze = (Maze) fromClient.readObject();
            SearchableMaze sMaze = new SearchableMaze(maze);
            byte[] list = maze.toByteArray();
            Solution solved;
            int ones = 0;
            for (byte b : list)
                if (b == 1) ones++;

            OutputStream out = new MyCompressorOutputStream(new FileOutputStream("savedMaze.maze"));
            out.write(list);
            out.flush();
            Path path = Paths.get("savedMaze.maze");

            String savedPath = mazeExists(String.valueOf(ones), Files.readAllBytes(path));
            if (savedPath != null) {
                solved = getSolution(savedPath);
            } else {
                Configurations conf = Configurations.getInstance();
                String search = conf.getProperty("mazeSearchingAlgorithm");
                ASearchingAlgorithm searchingAlg;
                switch (search) {
                    case "BreadthFirstSearch":
                        searchingAlg = new BreadthFirstSearch();
                        break;
                    case "DepthFirstSearch":
                        searchingAlg = new DepthFirstSearch();
                        break;
                    default:
                        searchingAlg = new BestFirstSearch();
                        break;
                }
                solved = searchingAlg.solve(sMaze);
                saveMaze(solved, list, ones);
            }
            toClient.writeObject(solved);
            toClient.flush();
            toClient.close();
            fromClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the maze and its solution to files
     * @param solved the solution to save
     * @param list the byte array representing the maze
     * @param ones the number of ones in the maze
     */
    private static void saveMaze(Solution solved, byte[] list, int ones) {
        try {
            File theDir = new File("Solved_Mazes");
            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            String onesName = checkFile(String.valueOf(ones));
            String filename = "Solved_Mazes/" + onesName;
            OutputStream out = new MyCompressorOutputStream(new FileOutputStream("tempMaze.maze"));
            out.write(list);
            out.flush();
            Path p = Paths.get("tempMaze.maze");
            byte[] compressedList = Files.readAllBytes(p);
            try (FileOutputStream fos = new FileOutputStream(filename)) {
                fos.write(compressedList);
            }
            PrintWriter writer = new PrintWriter("Solved_Mazes/" + "Solution_" + onesName + ".txt", "UTF-8");
            writer.println(solved.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks for existing files and returns a string representing the file name.
     * @param num the number of ones in the maze
     * @return a string that represents the file name
     */
    private static String checkFile(String num) {
        File dir = new File("Solved_Mazes");
        File[] directoryListing = dir.listFiles();
        int count = 1;
        if (directoryListing != null) {
            for (File child : directoryListing) {
                if (parseString(child.getName()).equals(num)) {
                    count++;
                }
            }
        } else {
            return num + "_1";
        }
        return num + "_" + count;
    }

    /**
     * Checks if the maze already exists and returns the name of the maze if it exists
     * @param ones the number of ones in the maze
     * @param list the byte array representing the maze
     * @return the name of the maze if it exists, null otherwise
     * @throws IOException
     */
    private static String mazeExists(String ones, byte[] list) throws IOException {
        File dir = new File("Solved_Mazes");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                if (parseString(child.getName()).equals(ones)) {
                    byte[] fileBytes = Files.readAllBytes(Paths.get(child.getPath()));
                    boolean flag = true;
                    for (int i = 0; i < fileBytes.length; i++) {
                        if (list[i] != fileBytes[i]) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) return child.getName();
                }
            }
        }
        return null;
    }

    /**
     * Returns a substring representing the initial part of a file name
     * @param str the string representing a file name
     * @return a substring representing the initial part of the file name
     */
    private static String parseString(String str) {
        int i = 0;
        while (str.charAt(i) != '_') i++;
        return str.substring(0, i);
    }

    /**
     * Returns the solution saved in a file
     * @param path the path to the file that holds the solution
     * @return the solution saved in the file
     * @throws IOException
     */
    private static Solution getSolution(String path) throws IOException {
        String mazeText = readFile("Solved_Mazes/" + "Solution_" + path + ".txt", StandardCharsets.UTF_8);
        ArrayList<AState> solutionPath = new ArrayList<>();
        int k = 0, counter = 0;
        String x = "", y = "";
        MazeState prevState = null;
        while (mazeText.charAt(k) != ':') k++;
        for (int i = k + 1; i < mazeText.length(); i++) {
            if (mazeText.charAt(i) == '{') {
                x = "";
                y = "";
                i++;
                while (mazeText.charAt(i) != ',') {
                    x += mazeText.charAt(i);
                    i++;
                }
                i++;
                while (mazeText.charAt(i) != '}') {
                    y += mazeText.charAt(i);
                    i++;
                }
                counter++;
                Position pos = new Position(Integer.parseInt(x), Integer.parseInt(y));
                MazeState currState = new MazeState(pos, prevState, 10);
                prevState = currState;
                solutionPath.add(currState);
            }
        }
        return new Solution(solutionPath);
    }

    /**
     * Reads the contents of a file into a string
     * @param path the path to the file
     * @param encoding the charset to use for decoding the file's bytes
     * @return the contents of the file as a string
     * @throws IOException
     */
    private static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
