package businesslogic;

import domain.Profesor;
import java.sql.SQLException;

/**
 *
 * @author liu
 */
public interface IProfesorDAO {
    public Profesor obtenerProfesor(Profesor profesor) throws SQLException;
    public Profesor obtenerProfesorQueIniciaSesion(Profesor profesor) throws SQLException;
    public boolean agregarProfesor(Profesor profesor) throws SQLException;
}
