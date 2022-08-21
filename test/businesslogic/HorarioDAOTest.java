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
    private ExperienciaEducativa experienciaEducativa1;
    private DiaDeLaSemana diaDeLaSemana1;
    private DiaDeLaSemana diaDeLaSemana2;
    private HorarioDAO horarioDAO;
    
    @Before
    public void inicializar() {
        horarioBusqueda = new Horario();
        experienciaEducativa1 = new ExperienciaEducativa(
            "80606",
            "TECNOLOGÍAS PARA LA CONSTRUCCIÓN DE SOFTWARE"
        );
        diaDeLaSemana1 = new DiaDeLaSemana(
            1,
            "LUNES"
        );
        diaDeLaSemana2 = new DiaDeLaSemana(
            2,
            "MARTES"
        );
        horario1 = new Horario(
            1,
            Time.valueOf("09:00:00"),
            Time.valueOf("11:00:00"),
            "F103",
            experienciaEducativa1,
            diaDeLaSemana2
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
