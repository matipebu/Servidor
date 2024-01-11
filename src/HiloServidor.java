import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HiloServidor implements Runnable {
    private static long contadorClientes = 0; 
    private Socket clientSocket;
    private ArrayList<Profesor> profesores;
    private long idCliente;

    public HiloServidor(Socket socket, ArrayList<Profesor> profesores) {
        this.clientSocket = socket;
        this.profesores = profesores;
        this.idCliente = incrementarContadorClientes();  
    }

    private synchronized long incrementarContadorClientes() {
        return ++contadorClientes;
    }

    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        long tiempoInicioCliente = System.currentTimeMillis();

        try {
           
            String clienteInfo = "Cliente: " + idCliente +
                    " iniciado, (" + obtenerFechaActual() + ")";
            System.out.println(clienteInfo);

            inputStream = new ObjectInputStream(clientSocket.getInputStream());
            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());

            while (true) {
                try {
                    // Lee el ID del cliente
                    String idProfesor = (String) inputStream.readObject();

                    // Imprime la consulta del cliente
                    String consulta = "Consultando id: " + idProfesor + ", solicitado por cliente: " +
                            idCliente;
                    System.out.println(consulta);

                    // Busca el profesor en la base de datos
                    Profesor profesorEncontrado = buscarProfesorPorId(idProfesor);

                    // Envía la información del profesor al cliente
                    if (profesorEncontrado != null) {
                        outputStream.writeObject(profesorEncontrado);
                    } else {
                        outputStream.writeObject("Profesor no encontrado");
                    }
                } catch (IOException | ClassNotFoundException e) {
                    // Cliente ha cerrado la conexión, salimos del bucle
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // Cierra los flujos y el socket para este cliente
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (clientSocket != null) {
                    clientSocket.close();
                }

                // Tiempo total conectado
                long tiempoTotalConectado = System.currentTimeMillis() - tiempoInicioCliente;
                String mensajeFin = "=>FIN con cliente: " + idCliente +
                        ", Tiempo total conectado: " + tiempoTotalConectado + " milisegundos (" +
                        obtenerFechaActual() + ")";
                System.out.println(mensajeFin);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Profesor buscarProfesorPorId(String idProfesor) {
        for (Profesor profesor : profesores) {
            if (String.valueOf(profesor.getIdprofesor()).equals(idProfesor)) {
                return profesor;
            }
        }
        return null;  // Si no se encuentra el profesor
    }
    

    private String obtenerFechaActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(new Date());
    }
}
