package businesslogic;

import domain.DiaDeLaSemana;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author liu
 */
public class DiaDeLaSemanaDAOTest {
    
    private DiaDeLaSemana diaBusqueda;
    private DiaDeLaSemana diaLunes;
    private DiaDeLaSemanaDAO diaDeLaSemanaDAO;
    
    @Before
    public void inicializar() {
        diaBusqueda = new DiaDeLaSemana();
        diaLunes = new DiaDeLaSemana(
            1,
            "LUNES");
        diaDeLaSemanaDAO = new DiaDeLaSemanaDAO();
    }
    
    @Test
    public void testObtenerDiaDeLaSemana() throws Exception {
        diaBusqueda.setId(1);
        DiaDeLaSemana diaDeLaSemanaObtenido = diaDeLaSemanaDAO.obtenerDiaDeLaSemana(diaBusqueda);
        assertTrue(diaDeLaSemanaObtenido.equals(diaLunes));
    }
    
}
