package domain;

import java.util.Objects;

/**
 *
 * @author liu
 */
public class DiaDeLaSemana {
    private int id;
    private String nombre;

    public DiaDeLaSemana() {
        this.id = 0;
        this.nombre = "";
    }

    public DiaDeLaSemana(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.nombre);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof DiaDeLaSemana) {
            DiaDeLaSemana tmpDiaDeLaSemana = (DiaDeLaSemana) obj;
            if(this.id == tmpDiaDeLaSemana.getId()
                && this.nombre.equals(tmpDiaDeLaSemana.getNombre())) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "DiaDeLaSemana{" + '\'' + 
            "id=" + id + '\'' +
            ", nombre=" + nombre + '\'' +
            '}';
    }    
}
