package businesslogic;

import domain.Horario;
import java.sql.SQLException;

/**
 *
 * @author liu
 */
public interface IHorarioDAO {
    public Horario obtenerHorario(Horario horario) throws SQLException;
}
