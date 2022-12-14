package businesslogic;

import dataaccess.ConexionBD;
import domain.Estudiante;
import domain.Persona;
import domain.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author liu
 */
public class EstudianteDAO implements IEstudianteDAO {

    @Override
    public Estudiante obtenerEstudiante(Estudiante estudiante) throws SQLException {
        Estudiante estudianteObtenido = new Estudiante();
        String consulta =
            "SELECT * FROM Estudiante WHERE matricula = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, estudiante.getMatricula());
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()) {
                estudianteObtenido = getEstudiante(resultado);
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return estudianteObtenido;
    }

    @Override
    public ObservableList<Estudiante> obtenerEstudiantes() throws SQLException {
        ObservableList<Estudiante> estudiantes = FXCollections.observableArrayList();
        String consulta =
            "SELECT * FROM Estudiante";
        ConexionBD baseDeDatos = new ConexionBD();
        
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            
            while(resultado.next()) {
                estudiantes.add(getEstudiante(resultado));
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return estudiantes;
    }

    @Override
    public boolean agregarEstudiante(Estudiante estudiante) throws SQLException {
        boolean estudianteAgregado = false;
        PersonaDAO personaDAO = new PersonaDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        Persona personaEstudiante = new Persona();
        personaEstudiante.setNombre(estudiante.getNombre());
        personaEstudiante.setApellidoPaterno(estudiante.getApellidoPaterno());
        personaEstudiante.setApellidoMaterno(estudiante.getApellidoMaterno());
        
        String consulta = "INSERT INTO Estudiante VALUES (?,?,?)";
        ConexionBD baseDeDatos = new ConexionBD();
        
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, estudiante.getMatricula());
            sentencia.setInt(2, personaDAO.agregarPersona(personaEstudiante));
            sentencia.setInt(3, usuarioDAO.agregarUsuario(estudiante.getUsuario()));
            int columnasAfectadas = sentencia.executeUpdate();
            
            if(columnasAfectadas != 0) {
                estudianteAgregado = true;
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return estudianteAgregado;
    }

    private Estudiante getEstudiante(ResultSet resultado) throws SQLException {
        String matricula;
        int idPersona;
        int idUsuario;
        Persona personaEstudiante = new Persona();
        PersonaDAO personaDAO = new PersonaDAO();
        Usuario usuarioEstudiante = new Usuario();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        matricula = resultado.getString("matricula");
        idPersona = resultado.getInt("idPersona");
        idUsuario = resultado.getInt("idUsuario");
        personaEstudiante.setIdPersona(idPersona);
        personaEstudiante = personaDAO.obtenerPersona(personaEstudiante);
        usuarioEstudiante.setId(idUsuario);
        usuarioEstudiante = usuarioDAO.obtenerUsuario(usuarioEstudiante);
        
        Estudiante estudiante = new Estudiante();
        estudiante.setMatricula(matricula);
        estudiante.setIdPersona(personaEstudiante.getIdPersona());
        estudiante.setNombre(personaEstudiante.getNombre());
        estudiante.setApellidoPaterno(personaEstudiante.getApellidoPaterno());
        estudiante.setApellidoMaterno(personaEstudiante.getApellidoMaterno());
        estudiante.setUsuario(usuarioEstudiante);
        
        return estudiante;
    }

    @Override
    public Estudiante obtenerEstudianteQueIniciaSesion(Estudiante estudiante) throws SQLException {
        Estudiante estudianteObtenido = new Estudiante();
        String consulta =
            "SELECT * FROM Estudiante WHERE idUsuario = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, estudiante.getUsuario().getId());
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()) {
                estudianteObtenido = getEstudiante(resultado);
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return estudianteObtenido;
    }
    
}
