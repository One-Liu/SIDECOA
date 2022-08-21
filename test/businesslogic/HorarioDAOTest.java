package businesslogic;

import domain.DiaDeLaSemana;
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
public class HorarioDAOTest {
    
    private Horario horarioBusqueda;
    private Horario horario1;
    private ExperienciaEducativa experienciaEducativaTecnologias;
    private DiaDeLaSemana diaLunes;
    private DiaDeLaSemana diaMartes;
    private HorarioDAO horarioDAO;
    
    @Before
    public void inicializar() {
        horarioBusqueda = new Horario();
        experienciaEducativaTecnologias = new ExperienciaEducativa(
            "80606",
            "TECNOLOGÍAS PARA LA CONSTRUCCIÓN DE SOFTWARE"
        );
        diaLunes = new DiaDeLaSemana(
            1,
            "LUNES"
        );
        diaMartes = new DiaDeLaSemana(
            2,
            "MARTES"
        );
        horario1 = new Horario(
            1,
            Time.valueOf("09:00:00"),
            Time.valueOf("11:00:00"),
            "F103",
            experienciaEducativaTecnologias,
            diaMartes
        );
        horarioDAO = new HorarioDAO();
    }
    
    @Test
    public void testObtenerHorario() throws Exception {
        horarioBusqueda.setId(1);
        Horario horarioObtenido = horarioDAO.obtenerHorario(horarioBusqueda);
        assertTrue(horarioObtenido.equals(horario1));
    }
}
