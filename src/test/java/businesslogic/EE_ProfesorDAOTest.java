package businesslogic;

import domain.EE_Profesor;
import domain.ExperienciaEducativa;
import domain.Profesor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
    
    @BeforeAll
    public void inicializar() {
        ee_ProfesorBusqueda = new EE_Profesor();
        ee_Profesor1 = new EE_Profesor(
            0,
            ee1 = new ExperienciaEducativa(
                "",
                ""
            ),
            profesor1 = new Profesor(
                "",
                0,
                "",
                "",
                ""
            )
        );
    }

    @Test
    public void testObtenerEE_Profesor() throws Exception {
        ee_ProfesorBusqueda.setId(0);
        EE_Profesor experienciaEducativa_ProfesorObtenido = ee_ProfesorDAO.obtenerEE_Profesor(ee_ProfesorBusqueda);
        assertTrue(experienciaEducativa_ProfesorObtenido.equals(ee_Profesor1));
    }
    
}
