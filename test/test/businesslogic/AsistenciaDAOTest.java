package test.businesslogic;

import businesslogic.AsistenciaDAO;
import domain.Asistencia;
import domain.DiaDeLaSemana;
import domain.Estudiante;
import domain.ExperienciaEducativa;
import domain.Horario;
import domain.Usuario;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author liu
 */
public class AsistenciaDAOTest {

    private Asistencia asistenciaBusqueda;
    private List<Asistencia> asistencias;
    private Asistencia asistencia1;
    private Asistencia asistencia2;
    private Asistencia asistencia3;
    private Usuario usuarioValeria;
    private Usuario usuarioJoseph;
    private Usuario usuarioSebastian;
    private Estudiante estudianteValeria;
    private Estudiante estudianteJoseph;
    private Estudiante estudianteSebastian;
    private Horario horario1;
    private ExperienciaEducativa experienciaEducativaTecnologias;
    private DiaDeLaSemana diaMartes;
    private AsistenciaDAO asistenciaDAO;
    
    @Before
    public void inicializar() {
        asistenciaBusqueda = new Asistencia();
        asistencias = new ArrayList<>();
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
        experienciaEducativaTecnologias = new ExperienciaEducativa(
            "80606",
            "TECNOLOGÍAS PARA LA CONSTRUCCIÓN DE SOFTWARE"
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
        asistencia1 = new Asistencia(
            1,
            estudianteValeria,
            horario1
        );
        asistencia2 = new Asistencia(
            2,
            estudianteJoseph,
            horario1
        );
        asistencia3 = new Asistencia(
            3,
            estudianteSebastian,
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
    
    @Test
    public void testObtenerAsistencias() throws Exception {
        asistenciaBusqueda.getHorario().getExperienciaEducativa().setNrc("80606");
        asistencias.add(asistencia1);
        asistencias.add(asistencia2);
        asistencias.add(asistencia3);
        List<Asistencia> asistenciasObtenidas = asistenciaDAO.obtenerAsistenciasPorEE(asistenciaBusqueda);
        assertTrue(asistenciasObtenidas.equals(asistencias));
    }
    
    @Test
    public void testHorarioEstudiante() throws Exception {
        asistenciaBusqueda.getEstudiante().setMatricula("S20015692");
        asistencias.add(asistencia1);
        List<Asistencia> asistenciasObtenidas = asistenciaDAO.obtenerHorarioEstudiante(asistenciaBusqueda);
        assertTrue(asistenciasObtenidas.equals(asistencias));
    }
}
