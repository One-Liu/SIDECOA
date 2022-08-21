package test.businesslogic;

import businesslogic.AsistenciaDAO;
import domain.Asistencia;
import domain.DiaDeLaSemana;
import domain.Estudiante;
import domain.ExperienciaEducativa;
import domain.Horario;
import domain.Usuario;
import java.sql.Time;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author liu
 */
public class AsistenciaDAOTest {

    private Asistencia asistenciaBusqueda;
    private Asistencia asistencia1;
    private Usuario usuarioValeria;
    private Estudiante estudianteValeria;
    private Horario horario1;
    private ExperienciaEducativa experienciaEducativaDiseno;
    private DiaDeLaSemana diaLunes;
    private AsistenciaDAO asistenciaDAO;
    
    @Before
    public void inicializar() {
        asistenciaBusqueda = new Asistencia();
        usuarioValeria = new Usuario(
            2, 
            "zS20015692@estudiantes.uv.mx", 
            "205f299406e46752e618b0e8c3248c5e9e6f00c3dd5e3c92d585d41ad70304aa"
        );
        estudianteValeria = new Estudiante(
            "S20015692",
            2,
            "VALERIA",
            "ABDALA",
            "GARCÍA",
            usuarioValeria
        );
        experienciaEducativaDiseno = new ExperienciaEducativa(
            "80606",
            "TECNOLOGÍAS PARA LA CONSTRUCCIÓN DE SOFTWARE"
        );
        diaLunes = new DiaDeLaSemana(
            2,
            "MARTES"
        );
        horario1 = new Horario(
            1,
            Time.valueOf("09:00:00"),
            Time.valueOf("11:00:00"),
            "F103",
            experienciaEducativaDiseno,
            diaLunes
        );
        asistencia1 = new Asistencia(
            1,
            estudianteValeria,
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
    
}
