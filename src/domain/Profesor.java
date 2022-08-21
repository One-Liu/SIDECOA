package domain;

import java.util.Objects;

/**
 *
 * @author liu
 */
public class Profesor extends Persona {
    private String numPersonal;
    private Usuario usuario;
    
    public Profesor() {
        super();
        this.numPersonal = "";
        this.usuario = new Usuario();
    }

    public Profesor(String numPersonal, int idPersona, String nombre, String apellidoPaterno, String apellidoMaterno, Usuario usuario) {
        super(idPersona, nombre, apellidoPaterno, apellidoMaterno);
        this.numPersonal = numPersonal;
        this.usuario = usuario;
    }

    public String getNumPersonal() {
        return numPersonal;
    }

    public void setNumPersonal(String numPersonal) {
        this.numPersonal = numPersonal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.numPersonal);
        hash = 79 * hash + Objects.hashCode(this.usuario);
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
                && this.apellidoMaterno.equals(tmpProfesor.getApellidoMaterno())
                && this.usuario.equals(tmpProfesor.getUsuario())) {
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
            ", usuario='" + usuario + '\'' +
            '}';
    }
}
