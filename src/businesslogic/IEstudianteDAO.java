package businesslogic;

import domain.Estudiante;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author liu
 */
public interface IEstudianteDAO {
    public Estudiante obtenerEstudiante(Estudiante estudiante) throws SQLException;
    public Estudiante obtenerEstudianteQueIniciaSesion(Estudiante estudiante) throws SQLException;
    public ObservableList<Estudiante> obtenerEstudiantes() throws SQLException;
    public boolean agregarEstudiante(Estudiante estudiante) throws SQLException;
}
