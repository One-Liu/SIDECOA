package businesslogic;

import domain.ExperienciaEducativa;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author liu
 */
public class ExperienciaEducativaDAOTest {
    
    private ExperienciaEducativa experienciaEducativaBusqueda;
    private ExperienciaEducativa experienciaEducativa1;
    private ExperienciaEducativaDAO experienciaEducativaDAO;
    
    @BeforeAll
    public void inicializar() {
        experienciaEducativaBusqueda = new ExperienciaEducativa();
        experienciaEducativa1 = new ExperienciaEducativa(
            "",
            ""
        );
        experienciaEducativaDAO = new ExperienciaEducativaDAO();
    }
    
    @Test
    public void testObtenerExperienciaEducativa() throws Exception {
        experienciaEducativaBusqueda.setNrc("");
        ExperienciaEducativa experienciaEducativaObtenida = experienciaEducativaDAO.obtenerExperienciaEducativa(experienciaEducativaBusqueda);
        assertTrue(experienciaEducativaObtenida.equals(experienciaEducativa1));
    }
    
}
