package domain;

import java.util.Objects;

/**
 *
 * @author liu
 */
public class Asistencia {
    private int id;
    private Estudiante estudiante;
    private Horario horario;

    public Asistencia() {
        this.id = 0;
        this.estudiante = new Estudiante();
        this.horario = new Horario();
    }

    public Asistencia(int id, Estudiante estudiante, Horario horario) {
        this.id = id;
        this.estudiante = estudiante;
        this.horario = horario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.id;
        hash = 43 * hash + Objects.hashCode(this.estudiante);
        hash = 43 * hash + Objects.hashCode(this.horario);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Asistencia) {
            Asistencia tmpAsistencia = (Asistencia) obj;
            if(this.id == tmpAsistencia.getId()
                && this.estudiante.equals(tmpAsistencia.getEstudiante())
                && this.horario.equals(tmpAsistencia.getHorario())) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Asistencia{" + '\'' +
            "id=" + id + '\'' +
            ", estudiante=" + estudiante + '\'' +
            ", horario=" + horario + '\'' +
            '}';
    }
}
