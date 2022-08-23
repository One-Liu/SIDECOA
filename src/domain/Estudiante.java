package domain;

import java.util.Objects;

/**
 *
 * @author liu
 */
public class Estudiante extends Persona {
    private String matricula;
    private Usuario usuario;
    
    public Estudiante() {
        super();
        this.matricula = "";
        this.usuario = new Usuario();
    }

    public Estudiante(String matricula, int idPersona, String nombre, String apellidoPaterno, String apellidoMaterno, Usuario usuario) {
        super(idPersona, nombre, apellidoPaterno, apellidoMaterno);
        this.matricula = matricula;
        this.usuario = usuario;
    }
    
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCorreoInstitucional() {
        return this.usuario.getCorreoInstitucional();
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.matricula);
        hash = 67 * hash + Objects.hashCode(this.usuario);
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
                && this.apellidoMaterno.equals(tmpEstudiante.getApellidoMaterno())
                && this.usuario.equals(tmpEstudiante.getUsuario())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Estudiante{" + '\'' +
            "matricula=" + matricula + '\'' +
            "idPersona=" + idPersona + '\'' +
            ", nombre='" + nombre + '\'' +
            ", apellidoPaterno='" + apellidoPaterno + '\'' +
            ", apellidoMaterno='" + apellidoMaterno + '\'' +
            ", usuario='" + usuario + '\'' +
            '}';
    }
}
