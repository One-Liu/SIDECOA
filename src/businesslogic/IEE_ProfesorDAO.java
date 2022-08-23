package businesslogic;

import domain.EE_Profesor;
import java.sql.SQLException;

/**
 *
 * @author liu
 */
public interface IEE_ProfesorDAO {
    public EE_Profesor obtenerEE_Profesor(EE_Profesor experienciaEducativa_Profesor) throws SQLException;
    public boolean asignarEE_Profesor(EE_Profesor experienciaEducativa_Profesor) throws SQLException;
    public boolean eeAsignadaAProfesor(EE_Profesor experienciaEducativa_Profesor) throws SQLException;
}
