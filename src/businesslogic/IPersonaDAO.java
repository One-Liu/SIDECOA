package businesslogic;

import domain.Persona;
import java.sql.SQLException;

/**
 *
 * @author liu
 */
public interface IPersonaDAO {
    public Persona obtenerPersona(Persona persona) throws SQLException;
    public int agregarPersona(Persona persona) throws SQLException;
}
