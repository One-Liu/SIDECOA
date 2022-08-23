package domain;

import java.sql.Time;
import java.util.Objects;

/**
 *
 * @author liu
 */
public class Horario {
    private int id;
    private Time horaInicio;
    private Time horaTermino;
    private String salon;
    private ExperienciaEducativa experienciaEducativa;
    private DiaDeLaSemana dia;

    public Horario() {
        this.id = 0;
        this.horaInicio = null;
        this.horaTermino = null;
        this.salon = "";
        this.experienciaEducativa = new ExperienciaEducativa();
        this.dia = new DiaDeLaSemana();
    }

    public Horario(int id, Time horaInicio, Time horaTermino, String salon, ExperienciaEducativa experienciaEducativa, DiaDeLaSemana dia) {
        this.id = id;
        this.horaInicio = horaInicio;
        this.horaTermino = horaTermino;
        this.salon = salon;
        this.experienciaEducativa = experienciaEducativa;
        this.dia = dia;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(Time horaTermino) {
        this.horaTermino = horaTermino;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public ExperienciaEducativa getExperienciaEducativa() {
        return experienciaEducativa;
    }

    public void setExperienciaEducativa(ExperienciaEducativa experienciaEducativa) {
        this.experienciaEducativa = experienciaEducativa;
    }

    public DiaDeLaSemana getDia() {
        return dia;
    }

    public void setDia(DiaDeLaSemana dia) {
        this.dia = dia;
    }
    
    public String getStringDia() {
        return this.dia.getNombre();
    }
    
    public String getNrc() {
        return this.experienciaEducativa.getNrc();
    }
    
    public String getNombreEE() {
        return this.experienciaEducativa.getNombre();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.id;
        hash = 13 * hash + Objects.hashCode(this.horaInicio);
        hash = 13 * hash + Objects.hashCode(this.horaTermino);
        hash = 13 * hash + Objects.hashCode(this.salon);
        hash = 13 * hash + Objects.hashCode(this.experienciaEducativa);
        hash = 13 * hash + Objects.hashCode(this.dia);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Horario) {
            Horario tmpHorario = (Horario) obj;
            if(this.id == tmpHorario.getId()
                && this.horaInicio.equals(tmpHorario.getHoraInicio())
                && this.horaTermino.equals(tmpHorario.getHoraTermino())
                && this.salon.equals(tmpHorario.getSalon())
                && this.experienciaEducativa.equals(tmpHorario.getExperienciaEducativa())
                && this.dia.equals(tmpHorario.getDia())) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Horario{" + '\'' + 
            "id=" + id + '\'' +
            ", horaInicio=" + horaInicio + '\'' +
            ", horaTermino=" + horaTermino + '\'' +
            ", salon=" + salon + '\'' + 
            ", experienciaEducativa=" + experienciaEducativa + '\'' +
            ", dia=" + dia + '\'' +
            '}';
    }
}
