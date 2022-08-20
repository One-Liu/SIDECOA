package businesslogic;

import domain.Asistencia;
import java.sql.SQLException;

/**
 *
 * @author liu
 */
public interface IAsistenciaDAO {
    public Asistencia obtenerAsistencia(Asistencia asistencia) throws SQLException;
}
