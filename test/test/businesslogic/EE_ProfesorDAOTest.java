package test.businesslogic;

import businesslogic.EE_ProfesorDAO;
import domain.EE_Profesor;
import domain.ExperienciaEducativa;
import domain.Profesor;
import domain.Usuario;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author liu
 */
public class EE_ProfesorDAOTest {
    
    private EE_Profesor ee_ProfesorBusqueda;
    private EE_Profesor ee_ProfesorNuevo;
    private EE_Profesor ee_Profesor1;
    private ExperienciaEducativa experienciaEducativaTecnologias;
    private Usuario usuarioJuanCarlos;
    private Profesor profesorJuanCarlos;
    private EE_ProfesorDAO ee_ProfesorDAO;
    
    @Before
    public void inicializar() {
        ee_ProfesorBusqueda = new EE_Profesor();
        ee_ProfesorNuevo = new EE_Profesor(
            0, 
            new ExperienciaEducativa(
                "", 
                ""
            ), 
            new Profesor(
                "", 
                0, 
                "", 
                "", 
                "", 
                new Usuario(
                    "", 
                    ""
                )
            )
        );
        experienciaEducativaTecnologias = new ExperienciaEducativa(
            "80606",
            "TECNOLOGÍAS PARA LA CONSTRUCCIÓN DE SOFTWARE"
        );
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
        ee_Profesor1 = new EE_Profesor(
            1,
            experienciaEducativaTecnologias,
            profesorJuanCarlos
        );
        ee_ProfesorDAO = new EE_ProfesorDAO();
    }

    @Test
    public void testObtenerEE_Profesor() throws Exception {
        ee_ProfesorBusqueda.setId(1);
        EE_Profesor experienciaEducativa_ProfesorObtenido = ee_ProfesorDAO.obtenerEE_Profesor(ee_ProfesorBusqueda);
        assertTrue(experienciaEducativa_ProfesorObtenido.equals(ee_Profesor1));
    }
    
    @Test
    public void testAsignarEE_Profesor() throws Exception {
        assertTrue(ee_ProfesorDAO.asignarEE_Profesor(ee_ProfesorNuevo));
    }
    
    @Test
    public void testEEAsignadaAProfesor() throws Exception {
        ee_ProfesorBusqueda.getExperienciaEducativa().setNrc("80606");
        assertTrue(ee_ProfesorDAO.eeAsignadaAProfesor(ee_ProfesorBusqueda));
    }
    
}
