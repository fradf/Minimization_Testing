package it.unisa.prioritization;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Dario
 */
public class ConfigManager {

    private static InputStream getPropertiesStream() throws IOException {
        File jarPath = new File(ConfigManager.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        String propertiesPath = jarPath.getParentFile().getAbsolutePath();

        InputStream inputStream = new FileInputStream(propertiesPath + File.separator + "config.properties");

        return inputStream;
    }

    public static String getExperimentBaseDirectory() throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = ConfigManager.getPropertiesStream();
        prop.load(inputStream);

        return prop.getProperty("experimentBaseDirectory");
    }

    public static String getInputPath() throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = ConfigManager.getPropertiesStream();
        prop.load(inputStream);
        String inputPath = prop.getProperty("inputPath");

        return inputPath;
    }

    public static String getOutputPath() throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = ConfigManager.getPropertiesStream();
        prop.load(inputStream);
        String outputPath = prop.getProperty("outputPath");

        return outputPath;
    }

    public static String getObjectives() throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = ConfigManager.getPropertiesStream();
        prop.load(inputStream);

        return prop.getProperty("objectives");
    }

    public static int getNumberOfObjectives() throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = ConfigManager.getPropertiesStream();
        prop.load(inputStream);

        //Every objective must be separated by a comma.
        //We have to consider also the cost that should not be added in the prop file.
        return StringUtils.countMatches(prop.getProperty("objectives"), ",") + 2;
    }

    public static int getNumberOfThreads() throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = ConfigManager.getPropertiesStream();
        prop.load(inputStream);

        return Integer.parseInt(prop.getProperty("threads"));
    }

    public static int getNumberOfIndipendentRuns() throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = ConfigManager.getPropertiesStream();
        prop.load(inputStream);

        return Integer.parseInt(prop.getProperty("runs"));
    }

    public static ArrayList<String> getAlgorithms() throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = ConfigManager.getPropertiesStream();
        prop.load(inputStream);

        String algorithmsString = prop.getProperty("algorithms");

        ArrayList<String> algorithmsList = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(algorithmsString, ",");
        while (st.hasMoreTokens()) {
            algorithmsList.add(st.nextToken());
        }

        return algorithmsList;
    }
}
