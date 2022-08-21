package domain;

import java.util.Objects;

/**
 *
 * @author liu
 */
public class Profesor extends Persona {
    private String numPersonal;
    
    public Profesor() {
        super();
        this.numPersonal = "";
    }

    public Profesor(String numPersonal) {
        this.numPersonal = numPersonal;
    }

    public Profesor(String numPersonal, int idPersona, String nombre, String apellidoPaterno, String apellidoMaterno) {
        super(idPersona, nombre, apellidoPaterno, apellidoMaterno);
        this.numPersonal = numPersonal;
    }

    public String getNumPersonal() {
        return numPersonal;
    }

    public void setNumPersonal(String numPersonal) {
        this.numPersonal = numPersonal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.numPersonal);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Profesor) {
            Profesor tmpProfesor = (Profesor) obj;
            if(this.numPersonal.equals(tmpProfesor.getNumPersonal())
                && this.idPersona == tmpProfesor.getIdPersona()
                && this.nombre.equals(tmpProfesor.getNombre())
                && this.apellidoPaterno.equals(tmpProfesor.getApellidoPaterno())
                && this.apellidoMaterno.equals(tmpProfesor.getApellidoMaterno())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Profesor{" + '\'' +
            "numPersonal=" + numPersonal + '\'' +
            "idPersona=" + idPersona + '\'' +
            ", nombre='" + nombre + '\'' +
            ", apellidoPaterno='" + apellidoPaterno + '\'' +
            ", apellidoMaterno='" + apellidoMaterno + '\'' +
            '}';
    }
}
