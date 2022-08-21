package businesslogic;

import domain.Usuario;
import java.sql.SQLException;

/**
 *
 * @author liu
 */
public interface IUsuarioDAO {
    public Usuario obtenerUsuario(Usuario usuario) throws SQLException;
    public Usuario obtenerUsuarioQueIniciaSesion(Usuario usuario) throws SQLException;
}
