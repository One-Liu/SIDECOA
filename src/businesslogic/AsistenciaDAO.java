package businesslogic;

import dataaccess.ConexionBD;
import domain.Asistencia;
import domain.Estudiante;
import domain.Horario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author liu
 */
public class AsistenciaDAO implements IAsistenciaDAO {

    @Override
    public Asistencia obtenerAsistencia(Asistencia asistencia) throws SQLException {
        Asistencia asistenciaObtenida = new Asistencia();
        String consulta =
            "SELECT * FROM Asistencia\n" +
            "WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, asistencia.getId());
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()) {
                asistenciaObtenida = getAsistencia(resultado);
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return asistenciaObtenida;
    }
    
    private Asistencia getAsistencia(ResultSet resultado) throws SQLException {
        int idAsistencia;
        String matricula;
        int idHorario;
        Estudiante estudiante = new Estudiante();
        EstudianteDAO estudianteDAO = new EstudianteDAO();
        Horario horario = new Horario();
        HorarioDAO horarioDAO = new HorarioDAO();
        
        idAsistencia = resultado.getInt("id");
        matricula = resultado.getString("matricula");
        idHorario = resultado.getInt("idHorario");
        estudiante.setMatricula(matricula);
        horario.setId(idHorario);
        estudiante = estudianteDAO.obtenerEstudiante(estudiante);
        horario = horarioDAO.obtenerHorario(horario);
        
        Asistencia asistencia = new Asistencia();
        asistencia.setId(idAsistencia);
        asistencia.setEstudiante(estudiante);
        asistencia.setHorario(horario);
        
        return asistencia;
    }
    
}
