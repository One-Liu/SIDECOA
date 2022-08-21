package businesslogic;

import domain.EE_Profesor;
import domain.ExperienciaEducativa;
import domain.Profesor;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author liu
 */
public class EE_ProfesorDAOTest {
    
    private EE_Profesor ee_ProfesorBusqueda;
    private EE_Profesor ee_Profesor1;
    private ExperienciaEducativa ee1;
    private Profesor profesor1;
    private EE_ProfesorDAO ee_ProfesorDAO;
    
    @Before
    public void inicializar() {
        ee_ProfesorBusqueda = new EE_Profesor();
        ee1 = new ExperienciaEducativa(
            "80606",
            "TECNOLOGÍAS PARA LA CONSTRUCCIÓN DE SOFTWARE"
        );
        profesor1 = new Profesor(
            "1234",
            1,
            "JUAN CARLOS",
            "PÉREZ",
            "ARRIAGA"
        );
        ee_Profesor1 = new EE_Profesor(
            1,
            ee1,
            profesor1
        );
        ee_ProfesorDAO = new EE_ProfesorDAO();
    }

    @Test
    public void testObtenerEE_Profesor() throws Exception {
        ee_ProfesorBusqueda.setId(1);
        EE_Profesor experienciaEducativa_ProfesorObtenido = ee_ProfesorDAO.obtenerEE_Profesor(ee_ProfesorBusqueda);
        assertTrue(experienciaEducativa_ProfesorObtenido.equals(ee_Profesor1));
    }
    
}
