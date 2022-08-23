package test.businesslogic;

import businesslogic.ExperienciaEducativaDAO;
import domain.ExperienciaEducativa;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author liu
 */
public class ExperienciaEducativaDAOTest {
    
    private ExperienciaEducativa experienciaEducativaBusqueda;
    private List<ExperienciaEducativa> experienciasEducativas;
    private ExperienciaEducativa experienciaEducativaDiseno;
    private ExperienciaEducativa experienciaEducativaPrueba;
    private ExperienciaEducativa experienciaEducativaTecnologias;
    private ExperienciaEducativaDAO experienciaEducativaDAO;
    
    @Before
    public void inicializar() {
        experienciaEducativaBusqueda = new ExperienciaEducativa();
        experienciaEducativaDiseno = new ExperienciaEducativa(
            "80602",
            "DISEÑO DE SOFTWARE"
        );
        experienciaEducativaPrueba = new ExperienciaEducativa(
            "80604",
            "PRUEBA DE SOFTWARE"
        );
        experienciaEducativaTecnologias = new ExperienciaEducativa(
            "80606",
            "TECNOLOGÍAS PARA LA CONSTRUCCIÓN DE SOFTWARE"
        );
        experienciasEducativas = new ArrayList<>();
        experienciasEducativas.add(experienciaEducativaDiseno);
        experienciasEducativas.add(experienciaEducativaPrueba);
        experienciasEducativas.add(experienciaEducativaTecnologias);
        experienciaEducativaDAO = new ExperienciaEducativaDAO();
    }
    
    @Test
    public void testObtenerExperienciaEducativa() throws Exception {
        experienciaEducativaBusqueda.setNrc("80606");
        ExperienciaEducativa experienciaEducativaObtenida = experienciaEducativaDAO.obtenerExperienciaEducativa(experienciaEducativaBusqueda);
        assertTrue(experienciaEducativaObtenida.equals(experienciaEducativaTecnologias));
    }
    
    @Test
    public void testObtenerExperienciasEducativas() throws Exception {
        List<ExperienciaEducativa> experienciasEducativasObtenidas = experienciaEducativaDAO.obtenerExperienciasEducativas();
        assertTrue(experienciasEducativasObtenidas.equals(experienciasEducativas));
    }
}
