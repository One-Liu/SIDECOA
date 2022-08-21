package businesslogic;

import domain.Estudiante;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author liu
 */
public class EstudianteDAOTest {
    
    private Estudiante estudianteBusqueda;
    private Estudiante estudianteNuevo;
    private Estudiante estudiante1;
    private Estudiante estudiante2;
    private Estudiante estudiante3;
    private Estudiante estudiante4;
    private Estudiante estudiante5;
    private Estudiante estudiante6;
    private EstudianteDAO estudianteDAO;
    
    @Before
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
        estudiante2 = new Estudiante(
            "S20015711",
            3,
            "JOSEPH HINYMOTO",
            "AGUILAR",
            "LÓPEZ"
        );
        estudiante3 = new Estudiante(
            "S20015730",
            5,
            "SEBASTIÁN",
            "BELLO",
            "TREJO"
        );
        estudiante4 = new Estudiante(
            "S20015753",
            6,
            "LEONARDO",
            "CRIOLLO",
            "RAMÍREZ"
        );
        estudiante5 = new Estudiante(
            "S20015736",
            8,
            "DANIELA",
            "MORALES",
            "SIXTO"
        );
        estudiante6 = new Estudiante(
            "S20015699",
            9,
            "ARMANDO OMAR",
            "OBANDO",
            "MUÑOZ"
        );
        estudianteDAO = new EstudianteDAO();
    }

    @Test
    public void testObtenerEstudiante() throws Exception {
        estudianteBusqueda.setMatricula("S20015730");
        Estudiante estudianteObtenido = estudianteDAO.obtenerEstudiante(estudianteBusqueda);
        assertTrue(estudianteObtenido.equals(estudiante3));
    }

    @Test
    public void testObtenerEstudiantes() throws Exception {
        ObservableList<Estudiante> estudiantesEsperados = FXCollections.observableArrayList();
        estudiantesEsperados.add(estudiante1);
        estudiantesEsperados.add(estudiante2);
        estudiantesEsperados.add(estudiante3);
        estudiantesEsperados.add(estudiante4);
        estudiantesEsperados.add(estudiante5);
        estudiantesEsperados.add(estudiante6);
        ObservableList<Estudiante> estudiantesObtenidos = estudianteDAO.obtenerEstudiantes();
        assertTrue(estudiantesEsperados.equals(estudiantesObtenidos));
    }
    
    @Test
    public void testAgregarEstudiante() throws Exception {
        assertTrue(estudianteDAO.agregarEstudiante(estudianteNuevo));
    }
}
