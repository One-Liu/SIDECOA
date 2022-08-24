package test.businesslogic;

import businesslogic.ExperienciaEducativaDAO;
import domain.ExperienciaEducativa;
import domain.Profesor;
import domain.Usuario;
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
    private Usuario usuarioJuanCarlos;
    private Profesor profesorJuanCarlos;
    private ExperienciaEducativa experienciaEducativaDiseno;
    private ExperienciaEducativa experienciaEducativaPrueba;
    private ExperienciaEducativa experienciaEducativaTecnologias;
    private ExperienciaEducativa experienciaEducativaDerecho;
    private ExperienciaEducativaDAO experienciaEducativaDAO;
    
    @Before
    public void inicializar() {
        experienciaEducativaBusqueda = new ExperienciaEducativa();
        usuarioJuanCarlos = new Usuario(
            1, 
            "juaperez@uv.mx", 
            "3a909744a8a3da4b97497ca78bc730d4eabfc41857efaa345f043641ad1b6dd3"
        );
        profesorJuanCarlos = new Profesor(
            "1234",
            1,
            "JUAN CARLOS",
            "PÉREZ",
            "ARRIAGA",
            usuarioJuanCarlos
        );
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
        experienciaEducativaDerecho = new ExperienciaEducativa(
            "80600",
            "DERECHO DE LAS TIC"
        );
        experienciasEducativas = new ArrayList<>();
        experienciaEducativaDAO = new ExperienciaEducativaDAO();
    }
    
    @Test
    public void testObtenerExperienciaEducativa() throws Exception {
        experienciaEducativaBusqueda.setNrc("80606");
        ExperienciaEducativa experienciaEducativaObtenida = experienciaEducativaDAO.obtenerExperienciaEducativa(experienciaEducativaBusqueda);
        assertTrue(experienciaEducativaObtenida.equals(experienciaEducativaTecnologias));
    }
    
    @Test
    public void testObtenerExperienciasEducativasSinProfesorAsignado() throws Exception {
        experienciasEducativas.add(experienciaEducativaDerecho);
        List<ExperienciaEducativa> experienciasEducativasObtenidas = experienciaEducativaDAO.obtenerExperienciasEducativasSinProfesorAsignado();
        assertTrue(experienciasEducativasObtenidas.equals(experienciasEducativas));
    }
    
    @Test
    public void testObtenerExperienciasEducativasDeProfesor() throws Exception {
        experienciasEducativas.add(experienciaEducativaTecnologias);
        List<ExperienciaEducativa> experienciasEducativasObtenidas = experienciaEducativaDAO.obtenerExperienciasEducativasDeProfesor(profesorJuanCarlos);
        assertTrue(experienciasEducativasObtenidas.equals(experienciasEducativas));
    }
}
