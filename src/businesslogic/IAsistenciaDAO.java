package businesslogic;

import domain.Asistencia;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author liu
 */
public interface IAsistenciaDAO {
    public Asistencia obtenerAsistencia(Asistencia asistencia) throws SQLException;
    public List<Asistencia> obtenerAsistenciasPorEE(Asistencia asistencia) throws SQLException;
    public List<Asistencia> obtenerHorarioEstudiante(Asistencia asistencia) throws SQLException;
}
