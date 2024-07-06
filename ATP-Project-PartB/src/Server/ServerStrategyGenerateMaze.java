package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.*;

import java.io.*;
import java.nio.file.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {

    /**
     * Server strategy that generates a maze based on the input dimensions and returns a compressed maze
     * in the form of a byte array
     * @param inFromClient the input stream from the client
     * @param outToClient  the output stream to the client
     */
    @Override
    public void handleClient(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            int[] mazeDimensions = (int[]) fromClient.readObject();

            Configurations config = Configurations.getInstance();
            String mazeGen = config.getProperty("mazeGenerationAlgorithm");
            AMazeGenerator mmg;

            if (mazeGen.equals("MyMazeGenerator")) { // Generate based on the configuration file
                mmg = new MyMazeGenerator();
            } else if (mazeGen.equals("SimpleMazeGenerator")) {
                mmg = new SimpleMazeGenerator();
            } else {
                mmg = new EmptyMazeGenerator();
            }

            Maze m = mmg.generate(mazeDimensions[0], mazeDimensions[1]);
            try (OutputStream out = new MyCompressorOutputStream(new FileOutputStream("savedMaze.maze"))) {
                out.write(m.toByteArray());
                out.flush();
            }

            Path path = Paths.get("savedMaze.maze");
            byte[] mazeCompressed = Files.readAllBytes(path);
            toClient.writeObject(mazeCompressed);
            toClient.flush();
            toClient.close();
            fromClient.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
