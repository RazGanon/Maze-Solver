package IO;

import java.io.IOException;
import java.io.OutputStream;

/**
 * SimpleCompressorOutputStream is a custom output stream that compresses a byte array
 * representing a maze by counting the number of consecutive ones and zeros.
 */
public class SimpleCompressorOutputStream extends OutputStream {
    private OutputStream out;

    /**
     * Constructs a SimpleCompressorOutputStream that wraps the specified output stream
     * @param out the output stream to wrap
     */
    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    /**
     * Writes the specified byte to this output stream
     * @param b the byte to write
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void write(int b) throws IOException {
        out.write((byte) b);
    }

    /**
     * Compresses and writes the specified byte array to this output stream.
     * The byte array is expected to represent a maze, and the compression is done
     * by counting the number of consecutive ones and zeros
     * @param b the byte array to compress and write
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void write(byte[] b) throws IOException {
        int index = 0;
        byte counter1 = -127, counter0 = -127;

        //Write initial part of the array until the delimiter (-2)
        while (b[index] != -2) {
            write(b[index]);
            index++;
        }
        write(b[index]); // write the delimiter
        index++;

        //Compress and write the remaining part of the array
        while (index < b.length) {
            while (index < b.length && b[index] == 0) {
                if (counter0 == 127) break;
                counter0++;
                index++;
            }
            write(counter0);
            counter0 = -127;
            if (index >= b.length) break;

            while (index < b.length && b[index] == 1) {
                if (counter1 == 127) break;
                counter1++;
                index++;
            }
            write(counter1);
            counter1 = -127;
        }
    }
}
