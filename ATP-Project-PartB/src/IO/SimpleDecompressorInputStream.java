package IO;

import java.io.IOException;
import java.io.InputStream;

/**
 * SimpleDecompressorInputStream is a custom input stream that decompresses a byte array
 * representing a maze from its compressed form.
 */
public class SimpleDecompressorInputStream extends InputStream {
    private InputStream in;

    /**
     * Constructs a SimpleDecompressorInputStream that wraps the specified input stream
     * @param in the input stream to wrap
     */
    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    /**
     * Reads the next byte of data from the input stream. This method is not implemented
     * @return the next byte of data, or -1 if the end of the stream is reached
     * @throws IOException if an I/O error occurs
     */
    @Override
    public int read() throws IOException {
        return 0;
    }

    /**
     * Decompresses the specified byte array from its compressed form.
     * The byte array is expected to represent a maze in its compressed form
     * @param b the byte array to decompress
     * @return 0 if the decompression is successful
     * @throws IOException if an I/O error occurs
     */
    @Override
    public int read(byte[] b) throws IOException {
        byte[] list = in.readAllBytes();
        int index = 0;

        //Copy initial part of the array until the delimiter (-2)
        while (list[index] != -2) {
            b[index] = list[index];
            index++;
        }
        b[index] = list[index];
        index++;
        int num, iterator = index;

        // Decompress and write the remaining part of the array
        while (index < list.length) {
            num = list[index] + 127;
            for (int k = 0; k < num; k++) {
                b[iterator] = 0;
                iterator++;
            }
            index++;
            if (index >= list.length) break;
            num = list[index] + 127;
            for (int k = 0; k < num; k++) {
                b[iterator] = 1;
                iterator++;
            }
            index++;
        }
        return 0;
    }
}
