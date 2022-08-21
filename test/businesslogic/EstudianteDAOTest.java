package businesslogic;

import domain.Estudiante;
import domain.Usuario;
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
    private Usuario usuarioNuevo;
    private Usuario usuarioValeria;
    private Usuario usuarioJoseph;
    private Usuario usuarioSebastian;
    private Usuario usuarioLeonardo;
    private Usuario usuarioDaniela;
    private Usuario usuarioArmando;
    private Estudiante estudianteValeria;
    private Estudiante estudianteJoseph;
    private Estudiante estudianteSebastian;
    private Estudiante estudianteLeonardo;
    private Estudiante estudianteDaniela;
    private Estudiante estudianteArmando;
    private EstudianteDAO estudianteDAO;
    
    @Before
    public void inicializar() {
        estudianteBusqueda = new Estudiante();
        estudianteNuevo = new Estudiante(
            "",
            0,
            "",
            "",
            "",
            usuarioNuevo
        );
        usuarioNuevo = new Usuario(
            0, 
            "", 
            ""
        );
        usuarioValeria = new Usuario(
            2, 
            "zS20015692@estudiantes.uv.mx", 
            "205f299406e46752e618b0e8c3248c5e9e6f00c3dd5e3c92d585d41ad70304aa"
        );
        usuarioJoseph = new Usuario(
            3, 
            "zS20015711@estudiantes.uv.mx", 
            "0fbe6c56c474709ac23b3b1bfba1c895ed985c8ccb19635d1565100bb1b7438c"
        );
        usuarioSebastian = new Usuario(
            5, 
            "zS20015730@estudiantes.uv.mx", 
            "383d5a9fa6be603311bdb55005a019cdd1fa4194f8afe1d3b63738828e40a3c4"
        );
        usuarioLeonardo = new Usuario(
            6, 
            "zS20015753@estudiantes.uv.mx", 
            "11717fec6ebddd22b8293ca5595ea420cf2acc8331d9495ff0442d131199e3b6"
        );
        usuarioDaniela = new Usuario(
            8, 
            "zS20015736@estudiantes.uv.mx", 
            "463d6893341c18c18407dd585eaae2db8781f10546d28c4fb3eadfb51d0e9890"
        );
        usuarioArmando = new Usuario(
            9, 
            "zS20015699@estudiantes.uv.mx", 
            "615d3cc23efc235f844f8497cb1e555cd9587d0206407a3fbb42074719bde201"
        );
        estudianteValeria = new Estudiante(
            "S20015692",
            2,
            "VALERIA",
            "ABDALA",
            "GARCÍA",
            usuarioValeria
        );
        estudianteJoseph = new Estudiante(
            "S20015711",
            3,
            "JOSEPH HINYMOTO",
            "AGUILAR",
            "LÓPEZ",
            usuarioJoseph
        );
        estudianteSebastian = new Estudiante(
            "S20015730",
            5,
            "SEBASTIÁN",
            "BELLO",
            "TREJO",
            usuarioSebastian
        );
        estudianteLeonardo = new Estudiante(
            "S20015753",
            6,
            "LEONARDO",
            "CRIOLLO",
            "RAMÍREZ",
            usuarioLeonardo
        );
        estudianteDaniela = new Estudiante(
            "S20015736",
            8,
            "DANIELA",
            "MORALES",
            "SIXTO",
            usuarioDaniela
        );
        estudianteArmando = new Estudiante(
            "S20015699",
            9,
            "ARMANDO OMAR",
            "OBANDO",
            "MUÑOZ",
            usuarioArmando
        );
        estudianteDAO = new EstudianteDAO();
    }

    @Test
    public void testObtenerEstudiante() throws Exception {
        estudianteBusqueda.setMatricula("S20015699");
        Estudiante estudianteObtenido = estudianteDAO.obtenerEstudiante(estudianteBusqueda);
        assertTrue(estudianteObtenido.equals(estudianteArmando));
    }

    @Test
    public void testObtenerEstudiantes() throws Exception {
        ObservableList<Estudiante> estudiantesEsperados = FXCollections.observableArrayList();
        estudiantesEsperados.add(estudianteValeria);
        estudiantesEsperados.add(estudianteArmando);
        estudiantesEsperados.add(estudianteJoseph);
        estudiantesEsperados.add(estudianteSebastian);
        estudiantesEsperados.add(estudianteDaniela);
        estudiantesEsperados.add(estudianteLeonardo);
        ObservableList<Estudiante> estudiantesObtenidos = estudianteDAO.obtenerEstudiantes();
        assertTrue(estudiantesEsperados.equals(estudiantesObtenidos));
    }
    
    @Test
    public void testAgregarEstudiante() throws Exception {
        assertTrue(estudianteDAO.agregarEstudiante(estudianteNuevo));
    }
}
