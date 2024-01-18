import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ServidorTCP {
    private static final int PUERTO = 12345;


    static ArrayList<Profesor> profesores = new ArrayList<>();
    static {
        Asignatura asignatura1 = new Asignatura(1, "AD");
        Asignatura asignatura2 = new Asignatura(2, "PMDM");
        Asignatura asignatura3 = new Asignatura(3, "PSP");
        Asignatura asignatura4 = new Asignatura(4, "SXE");
        Asignatura asignatura5 = new Asignatura(5, "DI");
        Asignatura asignatura6 = new Asignatura(4, "BD");
        Asignatura asignatura7 = new Asignatura(2, "PRG");
        Asignatura asignatura8 = new Asignatura(2, "LM");
        Asignatura asignatura9 = new Asignatura(2, "CD");


        Especialidad especialidad = new Especialidad(1, "AD");
        Especialidad especialidad2 = new Especialidad(1, "PRG");
        Especialidad especialidad3 = new Especialidad(1, "LM");

        profesores.add(new Profesor(1, "Juan", new Asignatura[]{asignatura1, asignatura2, asignatura3}, especialidad));
        profesores.add(new Profesor(2, "Pepe", new Asignatura[]{asignatura6, asignatura9, asignatura4}, especialidad2));
        profesores.add(new Profesor(3, "Manuel", new Asignatura[]{asignatura5, asignatura2, asignatura6}, especialidad));
        profesores.add(new Profesor(4, "Victor", new Asignatura[]{asignatura8, asignatura5, asignatura7}, especialidad3));
    }


      public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor esperando conexiones...");

            while (true) {
                Socket clientSocket = serverSocket.accept();

                
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                String dateStr = sdf.format(new Date());
                
        
                HiloServidor hiloServidor = new HiloServidor(clientSocket, profesores);
                Thread clientHandlerThread = new Thread(hiloServidor);
                clientHandlerThread.start();

               
                long idCliente = hiloServidor.getIdCliente();
                log("Cliente: " + idCliente + " iniciado, (" + dateStr + ")");
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null && !serverSocket.isClosed()) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static void log(String message) {
        objetoCompartido.log(message);
    }

    




}