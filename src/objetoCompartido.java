import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class objetoCompartido {
    private static final String LOG_FILE_PATH = "src/FichLog.txt";

    // Método para registrar información en el archivo
    public static void log(String message) {
        try {
            File logFile = new File(LOG_FILE_PATH);

            // Crea el archivo si no existe
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
