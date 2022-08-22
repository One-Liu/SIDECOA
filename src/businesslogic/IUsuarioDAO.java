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
    public int agregarUsuario(Usuario usuario) throws SQLException;
    public boolean validarUsuarioRegistrado(Usuario usuario) throws SQLException;
}
