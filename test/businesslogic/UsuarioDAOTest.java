package businesslogic;

import domain.Profesor;
import domain.Usuario;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author liu
 */
public class UsuarioDAOTest {
    
    private Usuario usuarioBusqueda;
    private Usuario usuario1;
    private Profesor profesor1;
    private UsuarioDAO usuarioDAO;
    
    @Before
    public void inicializar() {
        usuarioBusqueda = new Usuario();
        profesor1 = new Profesor(
            "",
            0,
            "",
            "",
            ""
        );
        usuario1 = new Usuario(
            0, 
            "", 
            "", 
            profesor1
        );
        usuarioDAO = new UsuarioDAO();
    }

    @Test
    public void testObtenerUsuario() throws Exception {
        usuarioBusqueda.setCorreoInstitucional("");
        usuarioBusqueda.setContrasenia("");
        Usuario usuarioObtenido = usuarioDAO.obtenerUsuario(usuarioBusqueda);
        assertTrue(usuarioObtenido.equals(usuario1));
    }    
}
