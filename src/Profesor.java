import java.io.Serializable;

public class Profesor implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idprofesor;
    private String nombre;
    private Asignatura[] asignaturas;
    private Especialidad espe;

    public Profesor(int idprofesor, String nombre, Asignatura[] asignaturas, Especialidad espe) {
        this.idprofesor = idprofesor;
        this.nombre = nombre;
        this.asignaturas = asignaturas;
        this.espe = espe;
    }


    // Getters y setters
    public int getIdprofesor() {
        return idprofesor;
    }

    public void setIdprofesor(int idprofesor) {
        this.idprofesor = idprofesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Asignatura[] getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(Asignatura[] asignaturas) {
        this.asignaturas = asignaturas;
    }

    public Especialidad getEspe() {
        return espe;
    }

    public void setEspe(Especialidad espe) {
        this.espe = espe;
    }
}
