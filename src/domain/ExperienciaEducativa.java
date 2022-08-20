package domain;

import java.util.Objects;

/**
 *
 * @author liu
 */
public class ExperienciaEducativa {
    private String nrc;
    private String nombre;

    public ExperienciaEducativa() {
        this.nrc = "";
        this.nombre = "";
    }

    public ExperienciaEducativa(String nrc, String nombre) {
        this.nrc = nrc;
        this.nombre = nombre;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.nrc);
        hash = 89 * hash + Objects.hashCode(this.nombre);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ExperienciaEducativa) {
            ExperienciaEducativa tmpExperienciaEducativa = (ExperienciaEducativa) obj;
            if(this.nrc.equals(tmpExperienciaEducativa.getNrc())
                && this.nombre.equals(tmpExperienciaEducativa.getNombre())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "ExperienciaEducativa{" + '\'' +
            "nrc=" + nrc + '\'' +
            ", nombre=" + nombre + '\'' + 
            '}';
    }
    
    
}
