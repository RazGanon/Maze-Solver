package Server;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Interface representing a strategy for server-client communication.
 * Implementations of this interface define the behavior of the server when
 * handling communication with a client.
 */
public interface IServerStrategy {

    /**
     * Defines the strategy for handling client communication.
     *
     * @param inFromClient the input stream from the client
     * @param outToClient  the output stream to the client
     */
    void handleClient(InputStream inFromClient, OutputStream outToClient);
}
