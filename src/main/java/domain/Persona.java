package domain;

import java.util.Objects;

/**
 *
 * @author liu
 */
public class Persona {
    protected int idPersona;
    protected String nombre;
    protected String apellidoPaterno;
    protected String apellidoMaterno;

    public Persona() {
        this.idPersona = 0;
        this.nombre = "";
        this.apellidoPaterno = "";
        this.apellidoMaterno = "";
    }

    public Persona(int idPersona, String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }
    

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombreCompleto() {
        return this.nombre + " " + this.apellidoPaterno + " " + this.apellidoMaterno;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.idPersona;
        hash = 29 * hash + Objects.hashCode(this.nombre);
        hash = 29 * hash + Objects.hashCode(this.apellidoPaterno);
        hash = 29 * hash + Objects.hashCode(this.apellidoMaterno);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Persona) {
            Persona tmpPersona = (Persona) obj;
            if(this.idPersona == tmpPersona.getIdPersona()
                    && this.nombre.equals(tmpPersona.getNombre())
                    && this.apellidoPaterno.equals(tmpPersona.getApellidoPaterno())
                    && this.apellidoMaterno.equals(tmpPersona.getApellidoMaterno())) {
                return true;
            } 
        }
        return false;
    }

    @Override
    public String toString() {
        return "Persona{" + '\'' +
                "idPersona=" + idPersona + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                '}';
    }
}
