package businesslogic;

import domain.DiaDeLaSemana;
import java.sql.SQLException;

/**
 *
 * @author liu
 */
public interface IDiaDeLaSemanaDAO {
    public DiaDeLaSemana obtenerDiaDeLaSemana(DiaDeLaSemana diaDeLaSemana) throws SQLException;
}
