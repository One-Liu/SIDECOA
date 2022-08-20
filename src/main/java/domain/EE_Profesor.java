package domain;

import java.util.Objects;

/**
 *
 * @author liu
 */
public class EE_Profesor {
    private int id;
    private ExperienciaEducativa experienciaEducativa;
    private Profesor profesor;

    public EE_Profesor() {
        this.id = 0;
        this.experienciaEducativa = new ExperienciaEducativa();
        this.profesor = new Profesor();
    }

    public EE_Profesor(int id, ExperienciaEducativa experienciaEducativa, Profesor profesor) {
        this.id = id;
        this.experienciaEducativa = experienciaEducativa;
        this.profesor = profesor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ExperienciaEducativa getExperienciaEducativa() {
        return experienciaEducativa;
    }

    public void setExperienciaEducativa(ExperienciaEducativa experienciaEducativa) {
        this.experienciaEducativa = experienciaEducativa;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + this.id;
        hash = 13 * hash + Objects.hashCode(this.experienciaEducativa);
        hash = 13 * hash + Objects.hashCode(this.profesor);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof EE_Profesor) {
            EE_Profesor tmpEE_Profesor = (EE_Profesor) obj;
            if(this.id == tmpEE_Profesor.getId()
                && this.experienciaEducativa.equals(tmpEE_Profesor.getExperienciaEducativa())
                && this.profesor.equals(tmpEE_Profesor.getProfesor())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "EE_Profesor{" + '\'' +
            "id=" + id + '\'' + 
            ", experienciaEducativa=" + experienciaEducativa + '\'' +
            ", profesor=" + profesor + '\'' +
            '}';
    }
}
