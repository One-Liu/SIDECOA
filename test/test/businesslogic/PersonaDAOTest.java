package test.businesslogic;

import businesslogic.PersonaDAO;
import domain.Persona;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author liu
 */
public class PersonaDAOTest {

    private Persona personaBusqueda;
    private Persona personaNueva;
    private Persona persona1;
    private PersonaDAO personaDAO;
    
    @Before
    public void inicializar() {
        personaBusqueda = new Persona();
        personaNueva = new Persona();
        persona1 = new Persona(
            1,
            "JUAN CARLOS",
            "PÉREZ",
            "ARRIAGA"
        );
        personaDAO = new PersonaDAO();
    }

    @Test
    public void testObtenerPersona() throws Exception {
        personaBusqueda.setIdPersona(1);
        Persona personaObtenida = personaDAO.obtenerPersona(personaBusqueda);
        assertTrue(personaObtenida.equals(persona1));
    }

    @Test
    public void testAgregarPersona() throws Exception {
        int idGenerado = 0;
        assertEquals(idGenerado, personaDAO.obtenerPersona(personaNueva));
    }
}
