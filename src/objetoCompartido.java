import java.io.*;




public class objetoCompartido  {
    private static final String LOG_FILE_PATH = "src/FichLog.txt";

    public static synchronized void log(String message) {
        try {
            File logFile = new File(LOG_FILE_PATH);

            if (!logFile.exists()) {
                logFile.createNewFile();
            }

            try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(logFile, true)))) {
                writer.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
