package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private volatile boolean stop;

    private ExecutorService threadPool;

    /**
     * Constructs a communication server
     * @param port the port number on which the server will listen
     * @param listeningIntervalMS the interval in milliseconds for server socket timeout
     * @param strategy the strategy for handling client communication
     */
    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        Configurations conf = Configurations.getInstance();
        String s = conf.getProperty("threadPoolSize");
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.threadPool = Executors.newFixedThreadPool(Integer.parseInt(s)); // amount of threads specified in the configuration file
    }

    /**
     * Starts the server in a new thread.
     */
    public void start() {
        new Thread(() -> startServer()).start();
    }

    /**
     * Runs the server, listening for client connections until a stop order is given.
     */
    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.setSoTimeout(listeningIntervalMS);
            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client accepted: " + clientSocket.toString());

                    threadPool.submit(() -> {
                        serverStrategy(clientSocket);
                    });
                } catch (IOException e) {
                    System.out.println("Connection Timed Out");
                }
            }
            threadPool.shutdown();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    /**
     * Applies the server strategy to handle client communication within the client socket
     * @param clientSocket the client socket
     */
    private void serverStrategy(Socket clientSocket) {
        try {
            strategy.handleClient(clientSocket.getInputStream(), clientSocket.getOutputStream());
            System.out.println("Done handling client: " + clientSocket.toString());
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    /**
     * Stops the server.
     */
    public void stop() {
        System.out.println("Stopping server...");
        stop = true;
    }
}
