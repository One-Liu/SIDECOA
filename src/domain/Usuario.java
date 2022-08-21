package domain;

import java.util.Objects;

/**
 *
 * @author liu
 */
public class Usuario {
    private int id;
    private String correoInstitucional;
    private String contrasenia;

    public Usuario() {
        this.id = 0;
        this.correoInstitucional = "";
        this.contrasenia = "";
    }

    public Usuario(int id, String correoInstitucional, String contrasenia) {
        this.id = id;
        this.correoInstitucional = correoInstitucional;
        this.contrasenia = contrasenia;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.correoInstitucional);
        hash = 97 * hash + Objects.hashCode(this.contrasenia);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Usuario) {
            Usuario tmpUsuario = (Usuario) obj;
            if(this.id == tmpUsuario.getId()
                && this.correoInstitucional.equals(tmpUsuario.getCorreoInstitucional())
                && this.contrasenia.equals(tmpUsuario.getContrasenia())) {
                return true;
            }
        } 
        return false;
    }

    @Override
    public String toString() {
        return "Usuario{" + '\'' +
            "id=" + id + '\'' +
            ", correoInstitucional=" + correoInstitucional + '\'' +
            ", contrasenia=" + contrasenia + '\'' +
            '}';
    }
}
