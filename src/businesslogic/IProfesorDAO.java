package businesslogic;

import domain.Profesor;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author liu
 */
public interface IProfesorDAO {
    public Profesor obtenerProfesor(Profesor profesor) throws SQLException;
    public Profesor obtenerProfesorQueIniciaSesion(Profesor profesor) throws SQLException;
    public List<Profesor> obtenerProfesores() throws SQLException;
    public boolean agregarProfesor(Profesor profesor) throws SQLException;
}
