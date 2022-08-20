package businesslogic;

import domain.Asistencia;
import domain.DiaDeLaSemana;
import domain.Estudiante;
import domain.ExperienciaEducativa;
import domain.Horario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
    
    @BeforeAll
    public void inicializar() {
        asistenciaBusqueda = new Asistencia();
        asistencia1 = new Asistencia(
            0,
            estudiante1 = new Estudiante(
                "",
                0,
                "",
                "",
                ""
            ),
            horario1 = new Horario(
                0,
                null,
                null,
                "",
                experienciaEducativa1 = new ExperienciaEducativa(
                    "",
                    ""
                ),
                diaDeLaSemana1 = new DiaDeLaSemana(
                    0,
                    ""
                )
            )
        );
        
        asistenciaDAO = new AsistenciaDAO();
    }

    @Test
    public void testObtenerAsistencia() throws Exception {
        asistenciaBusqueda.setId(0);
        Asistencia asistenciObtenida = asistenciaDAO.obtenerAsistencia(asistenciaBusqueda);
        assertTrue(asistenciObtenida.equals(asistencia1));
    }
    
}
