package test.businesslogic;

import businesslogic.UsuarioDAO;
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
    private Usuario usuarioJuanCarlos;
    private Usuario usuarioValeria;
    private UsuarioDAO usuarioDAO;
    
    @Before
    public void inicializar() {
        usuarioBusqueda = new Usuario();
        usuarioJuanCarlos = new Usuario(
            1, 
            "juaperez@uv.mx", 
            "3a909744a8a3da4b97497ca78bc730d4eabfc41857efaa345f043641ad1b6dd3"
        );
        usuarioValeria = new Usuario(
            2, 
            "zS20015692@estudiantes.uv.mx", 
            "205f299406e46752e618b0e8c3248c5e9e6f00c3dd5e3c92d585d41ad70304aa"
        );
        usuarioDAO = new UsuarioDAO();
    }

    @Test
    public void testObtenerUsuario() throws Exception {
        usuarioBusqueda.setId(1);
        Usuario usuarioObtenido = usuarioDAO.obtenerUsuario(usuarioBusqueda);
        assertTrue(usuarioObtenido.equals(usuarioJuanCarlos));
    }    
    
    @Test
    public void testObtenerUsuarioQueIniciaSesion() throws Exception {
        usuarioBusqueda.setCorreoInstitucional("zS20015692@estudiantes.uv.mx");
        usuarioBusqueda.setContrasenia("v4l3r14");
        Usuario usuarioObtenido = usuarioDAO.obtenerUsuarioQueIniciaSesion(usuarioBusqueda);
        assertTrue(usuarioObtenido.equals(usuarioValeria));
    }    
}
