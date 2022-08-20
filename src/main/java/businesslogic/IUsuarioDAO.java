package businesslogic;

import domain.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author liu
 */
public interface IUsuarioDAO {
    public Usuario obtenerUsuario(Usuario usuario) throws SQLException;
}
