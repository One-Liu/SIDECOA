package businesslogic;

import domain.Horario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author liu
 */
public class HorarioDAOTest {
    
    private Horario horarioBusqueda;
    private Horario horario1;
    private HorarioDAO horarioDAO;
    
    @BeforeAll
    public void inicializar() {
        horarioBusqueda = new Horario();
        horario1 = new Horario();
        horarioDAO = new HorarioDAO();
    }
    
    @Test
    public void testObtenerHorario() throws Exception {
        
    }
    
}
