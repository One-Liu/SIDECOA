package businesslogic;

import domain.Asistencia;
import domain.DiaDeLaSemana;
import domain.Estudiante;
import domain.ExperienciaEducativa;
import domain.Horario;
import java.sql.Time;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author liu
 */
public class AsistenciaDAOTest {

    private Asistencia asistenciaBusqueda;
    private Asistencia asistencia1;
    private Estudiante estudiante1;
    private Horario horario1;
    private ExperienciaEducativa experienciaEducativa1;
    private DiaDeLaSemana diaDeLaSemana1;
    private AsistenciaDAO asistenciaDAO;
    
    @Before
    public void inicializar() {
        asistenciaBusqueda = new Asistencia();
        estudiante1 = new Estudiante(
            "S20015692",
            2,
            "VALERIA",
            "ABDALA",
            "GARCÍA"
        );
        experienciaEducativa1 = new ExperienciaEducativa(
            "80606",
            "TECNOLOGÍAS PARA LA CONSTRUCCIÓN DE SOFTWARE"
        );
        diaDeLaSemana1 = new DiaDeLaSemana(
            2,
            "MARTES"
        );
        horario1 = new Horario(
            1,
            Time.valueOf("09:00:00"),
            Time.valueOf("11:00:00"),
            "F103",
            experienciaEducativa1,
            diaDeLaSemana1
        );
        asistencia1 = new Asistencia(
            1,
            estudiante1,
            horario1
        );
        
        asistenciaDAO = new AsistenciaDAO();
    }

    @Test
    public void testObtenerAsistencia() throws Exception {
        asistenciaBusqueda.setId(1);
        Asistencia asistenciObtenida = asistenciaDAO.obtenerAsistencia(asistenciaBusqueda);
        assertTrue(asistenciObtenida.equals(asistencia1));
    }
    
}
