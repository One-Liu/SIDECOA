package domain;

import java.util.Objects;

/**
 *
 * @author liu
 */
public class Estudiante extends Persona {
    private String matricula;

    public Estudiante() {
        super();
        this.matricula = "";
    }

    public Estudiante(String matricula) {
        this.matricula = matricula;
    }

    public Estudiante(String matricula, int idPersona, String nombre, String apellidoPaterno, String apellidoMaterno) {
        super(idPersona, nombre, apellidoPaterno, apellidoMaterno);
        this.matricula = matricula;
    }
    
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.matricula);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Estudiante) {
            Estudiante tmpEstudiante = (Estudiante) obj;
            if(this.matricula.equals(tmpEstudiante.getMatricula())
                && this.idPersona == tmpEstudiante.getIdPersona()
                && this.nombre.equals(tmpEstudiante.getNombre())
                && this.apellidoPaterno.equals(tmpEstudiante.getApellidoPaterno())
                && this.apellidoMaterno.equals(tmpEstudiante.getApellidoMaterno())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Profesor{" + '\'' +
            "matricula=" + matricula + '\'' +
            "idPersona=" + idPersona + '\'' +
            ", nombre='" + nombre + '\'' +
            ", apellidoPaterno='" + apellidoPaterno + '\'' +
            ", apellidoMaterno='" + apellidoMaterno + '\'' +
            '}';
    }
}
