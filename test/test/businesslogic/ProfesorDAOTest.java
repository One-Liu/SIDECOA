package test.businesslogic;

import businesslogic.ProfesorDAO;
import domain.Profesor;
import domain.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author liu
 */
public class ProfesorDAOTest {
    
    private Profesor profesorBusqueda;
    private Profesor profesorNuevo;
    private List<Profesor> profesores;
    private Usuario usuarioJuanCarlos;
    private Usuario usuarioAnaLuz;
    private Usuario usuarioLourdes;
    private Profesor profesorJuanCarlos;
    private Profesor profesorAnaLuz;
    private Profesor profesorLourdes;
    private ProfesorDAO profesorDAO;
    
    @Before
    public void inicializar() {
        profesorBusqueda = new Profesor();
        profesorNuevo = new Profesor();
        usuarioJuanCarlos = new Usuario(
            1, 
            "juaperez@uv.mx", 
            "3a909744a8a3da4b97497ca78bc730d4eabfc41857efaa345f043641ad1b6dd3"
        );
        usuarioAnaLuz = new Usuario(
            4, 
            "apolo@uv.mx", 
            "d89d2563b0e15f2f76e7d88efad4cef900c35c56c09a065d04ebc046eb7a3561"
        );
        usuarioLourdes = new Usuario(
            7, 
            "lourhernandez@uv.mx", 
            "8c513803e8a2b5fa17ff755dacb6ba5524b471410707bd4dfda59219849b8fed"
        );
        profesorJuanCarlos = new Profesor(
            "1234",
            1,
            "JUAN CARLOS",
            "PÉREZ",
            "ARRIAGA",
            usuarioJuanCarlos
        );
        profesorAnaLuz = new Profesor(
            "1235",
            4,
            "ANA LUZ",
            "POLO",
            "ESTRELLA",
            usuarioAnaLuz
        );
        profesorLourdes = new Profesor(
            "1236",
            7,
            "MARÍA DE LOURDES",
            "HERNÁNDEZ",
            "RODRÍGUEZ",
            usuarioLourdes
        );
        profesores = new ArrayList<>();
        profesores.add(profesorJuanCarlos);
        profesores.add(profesorAnaLuz);
        profesores.add(profesorLourdes);
        profesorDAO = new ProfesorDAO();
    }

    @Test
    public void testObtenerProfesor() throws Exception {
        profesorBusqueda.setNumPersonal("1234");
        Profesor profesorObtenido = profesorDAO.obtenerProfesor(profesorBusqueda);
        assertTrue(profesorObtenido.equals(profesorJuanCarlos));
    }

    @Test
    public void testAgregarProfesor() throws Exception {
        assertTrue(profesorDAO.agregarProfesor(profesorNuevo));
    }
    
    @Test
    public void testObtenerProfesorQueIniciaSesion() throws Exception {
        profesorBusqueda.setUsuario(usuarioJuanCarlos);
        Profesor profesorObtenido = profesorDAO.obtenerProfesorQueIniciaSesion(profesorBusqueda);
        assertTrue(profesorObtenido.equals(profesorJuanCarlos));
    }
    
    @Test
    public void testObtenerProfesores() throws Exception {
        List<Profesor> profesoresObtenidos = profesorDAO.obtenerProfesores();
        assertTrue(profesoresObtenidos.equals(profesores));
    }
    
}
