package businesslogic;

import dataaccess.ConexionBD;
import domain.Asistencia;
import domain.Estudiante;
import domain.Horario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author liu
 */
public class AsistenciaDAO implements IAsistenciaDAO {

    @Override
    public Asistencia obtenerAsistencia(Asistencia asistencia) throws SQLException {
        Asistencia asistenciaObtenida = new Asistencia();
        String consulta =
            "SELECT * FROM Asistencia WHERE id = ?";
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

    @Override
    public List<Asistencia> obtenerAsistenciasPorEE(Asistencia asistencia) throws SQLException {
        List<Asistencia> asistenciasObtenida = new ArrayList<>();
        String consulta = 
            "SELECT * FROM Asistencia A \n" +
            "LEFT JOIN Horario H ON H.id = A.idHorario \n" +
            "LEFT JOIN ExperienciaEducativa EE ON EE.nrc = H.nrc \n" +
            "WHERE EE.nrc = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, asistencia.getHorario().getExperienciaEducativa().getNrc());
            ResultSet resultado = sentencia.executeQuery();
            
            while(resultado.next()) {
                asistenciasObtenida.add(getAsistencia(resultado));
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return asistenciasObtenida;
    }

    @Override
    public List<Asistencia> obtenerHorarioEstudiante(Asistencia asistencia) throws SQLException {
        List<Asistencia> asistenciasObtenida = new ArrayList<>();
        String consulta = 
            "SELECT * FROM Asistencia \n" +
            "WHERE matricula = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, asistencia.getEstudiante().getMatricula());
            ResultSet resultado = sentencia.executeQuery();
            
            while(resultado.next()) {
                asistenciasObtenida.add(getAsistencia(resultado));
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return asistenciasObtenida;    }
}
