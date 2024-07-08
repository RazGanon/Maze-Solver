package Server;

import java.io.*;
import java.util.Properties;

public class Configurations {

    private static Configurations instance = null;
    private static Properties prop;

    /**
     * Returns the singleton instance of the Configurations class
     * @return the singleton instance of Configurations
     */
    public static Configurations getInstance() {
        if (instance == null) {
            instance = new Configurations();
        }
        return instance;
    }

    /**
     * Private constructor to prevent instantiation.
     * Initializes the Properties object.
     */
    private Configurations() {
        prop = new Properties();
    }

    /**
     * Returns the value of the specified property key
     * @param name the property key
     * @return the property value, or null if the key is not found
     */
    public String getProperty(String name) {
        try (InputStream input = new FileInputStream("resources/config.properties")) {
            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop.getProperty(name);
    }

    /**
     * Sets a new property with the specified key and value
     * @param key   the property key
     * @param value the property value
     */
    public void setProperty(String key, String value) {
        try (OutputStream output = new FileOutputStream("resources/config.properties")) {
            prop.setProperty(key, value);
            prop.store(output, "");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
