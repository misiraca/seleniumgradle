package utils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Dragutin Misiraca
 */
public class ConfigProperties {

    private static final Properties PROPERTIES = readPropertiesFile();
    public static final String ENV_FRONTEND = PROPERTIES.getProperty("ENV");
    public static final String BROWSER_NAME=PROPERTIES.getProperty("BROWSER_NAME"); // CHROME, FIREFOX, SAFARI
    public static final int DEFAULT_TIMEOUT = Integer.parseInt(PROPERTIES.getProperty("DEFAULT_TIMEOUT"));



    private static Properties readPropertiesFile() {
        String result = "";
        InputStream inputStream;

        Properties properties = new Properties();
        String propFileName = "config.properties";
        try {
            inputStream = ConfigProperties.class.getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return properties;
    }
}