package businesslogic;

import domain.Estudiante;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author liu
 */
public class EstudianteDAOTest {
    
    private Estudiante estudianteBusqueda;
    private Estudiante estudianteNuevo;
    private Estudiante estudiante1;
    private EstudianteDAO estudianteDAO;
    
    @BeforeAll
    public void inicializar() {
        estudianteBusqueda = new Estudiante();
        estudianteNuevo = new Estudiante(
            "",
            0,
            "",
            "",
            ""
        );
        estudiante1 = new Estudiante(
            "S20015692",
            2,
            "VALERIA",
            "ABDALA",
            "GARCÍA"
        );
        estudianteDAO = new EstudianteDAO();
    }

    @Test
    public void testObtenerEstudiante() throws Exception {
        estudianteBusqueda.setMatricula("S20015692");
        Estudiante estudianteObtenido = estudianteDAO.obtenerEstudiante(estudianteBusqueda);
        assertTrue(estudianteObtenido.equals(estudiante1));
    }

    @Test
    public void testObtenerEstudiantes() throws Exception {
        ObservableList<Estudiante> estudiantesEsperados = FXCollections.observableArrayList();
        estudiantesEsperados.add(estudiante1);
        ObservableList<Estudiante> estudiantesObtenidos = estudianteDAO.obtenerEstudiantes();
        assertTrue(estudiantesEsperados.equals(estudiantesObtenidos));
    }

    /*
    @Test
    public void testAgregarEstudiante() throws Exception {
        assertTrue(estudianteDAO.agregarEstudiante(estudianteNuevo));
    }
    */
}
