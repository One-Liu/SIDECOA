package businesslogic;

import domain.ExperienciaEducativa;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author liu
 */
public class ExperienciaEducativaDAOTest {
    
    private ExperienciaEducativa experienciaEducativaBusqueda;
    private ExperienciaEducativa experienciaEducativa1;
    private ExperienciaEducativa experienciaEducativa2;
    private ExperienciaEducativa experienciaEducativa3;
    private ExperienciaEducativaDAO experienciaEducativaDAO;
    
    @Before
    public void inicializar() {
        experienciaEducativaBusqueda = new ExperienciaEducativa();
        experienciaEducativa1 = new ExperienciaEducativa(
            "80602",
            "DISEÑO DE SOFTWARE"
        );
        experienciaEducativa2 = new ExperienciaEducativa(
            "80604",
            "PRUEBA DE SOFTWARE"
        );
        experienciaEducativa3 = new ExperienciaEducativa(
            "80606",
            "TECNOLOGÍAS PARA LA CONSTRUCCIÓN DE SOFTWARE"
        );
        experienciaEducativaDAO = new ExperienciaEducativaDAO();
    }
    
    @Test
    public void testObtenerExperienciaEducativa() throws Exception {
        experienciaEducativaBusqueda.setNrc("80606");
        ExperienciaEducativa experienciaEducativaObtenida = experienciaEducativaDAO.obtenerExperienciaEducativa(experienciaEducativaBusqueda);
        assertTrue(experienciaEducativaObtenida.equals(experienciaEducativa3));
    }
}
