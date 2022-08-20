package domain;

/**
 *
 * @author liu
 */
public class DatosGlobalesDeSesion {
    private static DatosGlobalesDeSesion instancia;
    private Profesor profesor;
    private Estudiante estudiante;
    
    public Profesor getProfesor() {
        return profesor;
    }
    
    public Estudiante getEstudiante() {
        return estudiante;
    }
    
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
        this.estudiante = null;
    }
    
    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
        this.profesor = null;
    }
    
    private DatosGlobalesDeSesion() {
        this.profesor = null;
        this.estudiante = null;
    }
    
    public static DatosGlobalesDeSesion getDatosGlobalesDeSesion() {
        if(instancia == null) {
            instancia = new DatosGlobalesDeSesion();
        }
        return instancia;
    }
}
