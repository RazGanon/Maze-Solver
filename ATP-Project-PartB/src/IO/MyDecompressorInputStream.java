package IO;

import java.io.IOException;
import java.io.InputStream;

/**
 * MyDecompressorInputStream is a custom input stream that decompresses a byte array
 * representing a maze from its compressed form.
 */
public class MyDecompressorInputStream extends InputStream {
    private InputStream in;

    /**
     * Constructs a MyDecompressorInputStream that wraps the specified input stream
     * @param in the input stream to wrap
     */
    public MyDecompressorInputStream(InputStream in) {
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
        int iterator = index;
        String binaryNum;

        //Decompress and write the remaining part of the array
        while (index < list.length) {
            binaryNum = String.format("%" + 7 + "s", Integer.toBinaryString(list[index])).replaceAll(" ", "0");
            StringBuilder rev = new StringBuilder();
            rev.append(binaryNum);
            rev.reverse();
            binaryNum = rev.toString();
            for (int i = 0; i < binaryNum.length(); i++) {
                if (iterator >= b.length) break;
                b[iterator] = (byte) (binaryNum.charAt(i) - '0');
                iterator++;
            }
            index++;
        }
        return 0;
    }
}
