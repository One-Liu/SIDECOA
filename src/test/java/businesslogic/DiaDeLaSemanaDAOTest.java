package businesslogic;

import domain.DiaDeLaSemana;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author liu
 */
public class DiaDeLaSemanaDAOTest {
    
    private DiaDeLaSemana diaBusqueda;
    private DiaDeLaSemana diaDeLaSemana1;
    private DiaDeLaSemanaDAO diaDeLaSemanaDAO;
    
    @BeforeAll
    public void inicializar() {
        diaBusqueda = new DiaDeLaSemana();
        diaDeLaSemana1 = new DiaDeLaSemana();
        diaDeLaSemana1.setId(1);
        diaDeLaSemana1.setNombre("LUNES");
        diaDeLaSemanaDAO = new DiaDeLaSemanaDAO();
    }
    
    @Test
    public void testObtenerDiaDeLaSemana() throws Exception {
        diaBusqueda.setId(1);
        DiaDeLaSemana diaDeLaSemanaObtenido = diaDeLaSemanaDAO.obtenerDiaDeLaSemana(diaBusqueda);
        assertTrue(diaDeLaSemanaObtenido.equals(diaDeLaSemana1));
    }
    
}
