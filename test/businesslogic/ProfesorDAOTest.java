package businesslogic;

import domain.Profesor;
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
    private Profesor profesor1;
    private Profesor profesor2;
    private Profesor profesor3;
    private ProfesorDAO profesorDAO;
    
    @Before
    public void inicializar() {
        profesorBusqueda = new Profesor();
        profesorNuevo = new Profesor();
        profesor1 = new Profesor(
            "1234",
            1,
            "JUAN CARLOS",
            "PÉREZ",
            "ARRIAGA"
        );
        profesor2 = new Profesor(
            "1235",
            4,
            "ANA LUZ",
            "POLO",
            "ESTRELLA"
        );
        profesor3 = new Profesor(
            "1236",
            7,
            "MARÍA DE LOURDES",
            "HERNÁNDEZ",
            "RODRÍGUEZ"
        );
        profesorDAO = new ProfesorDAO();
    }

    @Test
    public void testObtenerProfesor() throws Exception {
        profesorBusqueda.setNumPersonal("1236");
        Profesor profesorObtenido = profesorDAO.obtenerProfesor(profesorBusqueda);
        assertTrue(profesorObtenido.equals(profesor3));
    }

    @Test
    public void testAgregarProfesor() throws Exception {
        assertTrue(profesorDAO.agregarProfesor(profesorNuevo));
    }
    
}
