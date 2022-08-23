package businesslogic;

import domain.ExperienciaEducativa;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author liu
 */
public interface IExperienciaEducativaDAO {
    public ExperienciaEducativa obtenerExperienciaEducativa(ExperienciaEducativa experienciaEducativa) throws SQLException;
    public List<ExperienciaEducativa> obtenerExperienciasEducativas() throws SQLException;
}
