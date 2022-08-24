package businesslogic;

import domain.ExperienciaEducativa;
import domain.Profesor;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author liu
 */
public interface IExperienciaEducativaDAO {
    public ExperienciaEducativa obtenerExperienciaEducativa(ExperienciaEducativa experienciaEducativa) throws SQLException;
    public List<ExperienciaEducativa> obtenerExperienciasEducativasSinProfesorAsignado() throws SQLException;
    public List<ExperienciaEducativa> obtenerExperienciasEducativasDeProfesor(Profesor profesor) throws SQLException;
}
