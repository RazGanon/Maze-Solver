package IO;

import java.io.IOException;
import java.io.OutputStream;

/**
 * MyCompressorOutputStream is a custom output stream that compresses a byte array representing
 * a maze by converting sequences of binary numbers to their decimal equivalents.
 */
public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;

    /**
     * Constructs a MyCompressorOutputStream that wraps the specified output stream
     * @param out the output stream to wrap
     */
    public MyCompressorOutputStream(OutputStream out) {
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
     * Compresses and writes the specified byte array to this output stream
     * The byte array is expected to represent a maze, where sequences of binary numbers
     * are converted to their decimal equivalents
     * @param b the byte array to compress and write
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void write(byte[] b) throws IOException {
        int index = 0;

        //Write initial part of the array until the delimiter (-2)
        while (b[index] != -2) {
            write(b[index]);
            index++;
        }
        write(b[index]); // write the delimiter
        index++;

        int num = 0;

        //Compress and write the remaining part of the array
        while (index < b.length) {
            for (int i = 0; i < 7; i++) {
                num += b[index] * Math.pow(2, i);
                index++;
                if (index >= b.length) break;
            }
            write(num);
            num = 0;
        }
    }
}
