package Client;

import java.io.*;

/**
 * Interface representing a strategy for client-server communication
 * Implementations of this interface define the behavior of the client when
 * communicating with the server.
 */
public interface IClientStrategy {

    /**
     * Defines the strategy for client-server communication
     * @param inFromServer the input stream from the server
     * @param outToServer  the output stream to the server
     */
    void clientStrategy(InputStream inFromServer, OutputStream outToServer);
}
