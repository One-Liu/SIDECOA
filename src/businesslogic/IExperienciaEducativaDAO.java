package businesslogic;

import domain.ExperienciaEducativa;
import java.sql.SQLException;

/**
 *
 * @author liu
 */
public interface IExperienciaEducativaDAO {
    public ExperienciaEducativa obtenerExperienciaEducativa(ExperienciaEducativa experienciaEducativa) throws SQLException;
}
