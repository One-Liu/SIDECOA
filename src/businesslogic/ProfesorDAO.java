package businesslogic;

import dataaccess.ConexionBD;
import domain.Persona;
import domain.Profesor;
import domain.Usuario;
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
public class ProfesorDAO implements IProfesorDAO {

    @Override
    public Profesor obtenerProfesor(Profesor profesor) throws SQLException {
        Profesor profesorObtenido = new Profesor();
        String consulta =
            "SELECT * FROM Profesor WHERE numPersonal = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, profesor.getNumPersonal());
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()) {
                profesorObtenido = getProfesor(resultado);
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return profesorObtenido;
    }

    @Override
    public boolean agregarProfesor(Profesor profesor) throws SQLException {
        boolean profesorAgregado = false;
        PersonaDAO personaDAO = new PersonaDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        Persona personaProfesor = new Persona();
        personaProfesor.setNombre(profesor.getNombre());
        personaProfesor.setApellidoPaterno(profesor.getApellidoPaterno());
        personaProfesor.setApellidoMaterno(profesor.getApellidoMaterno());
        
        String consulta = "INSERT INTO Profesor VALUES (?,?,?)";
        ConexionBD baseDeDatos = new ConexionBD();
        
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, profesor.getNumPersonal());
            sentencia.setInt(2, personaDAO.agregarPersona(personaProfesor));
            sentencia.setInt(3, usuarioDAO.agregarUsuario(profesor.getUsuario()));
            int columnasAfectadas = sentencia.executeUpdate();
            
            if(columnasAfectadas != 0) {
                profesorAgregado = true;
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return profesorAgregado;
    }

    private Profesor getProfesor(ResultSet resultado) throws SQLException {
        String numPersonal;
        int idPersona;
        int idUsuario;
        Persona personaProfesor = new Persona();
        PersonaDAO personaDAO = new PersonaDAO();
        Usuario usuarioProfesor = new Usuario();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        numPersonal = resultado.getString("numPersonal");
        idPersona = resultado.getInt("idPersona");
        idUsuario = resultado.getInt("idUsuario");
        personaProfesor.setIdPersona(idPersona);
        personaProfesor = personaDAO.obtenerPersona(personaProfesor);
        usuarioProfesor.setId(idUsuario);
        usuarioProfesor = usuarioDAO.obtenerUsuario(usuarioProfesor);
        
        Profesor profesor = new Profesor();
        profesor.setNumPersonal(numPersonal);
        profesor.setIdPersona(personaProfesor.getIdPersona());
        profesor.setNombre(personaProfesor.getNombre());
        profesor.setApellidoPaterno(personaProfesor.getApellidoPaterno());
        profesor.setApellidoMaterno(personaProfesor.getApellidoMaterno());
        profesor.setUsuario(usuarioProfesor);
        
        return profesor;
    }

    @Override
    public Profesor obtenerProfesorQueIniciaSesion(Profesor profesor) throws SQLException {
        Profesor profesorObtenido = new Profesor();
        String consulta =
            "SELECT * FROM Profesor WHERE idUsuario = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, profesor.getUsuario().getId());
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()) {
                profesorObtenido = getProfesor(resultado);
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return profesorObtenido;
    }

    @Override
    public List<Profesor> obtenerProfesores() throws SQLException {
        List<Profesor> profesores = new ArrayList<>();
        String consulta = "SELECT * FROM Profesor";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            while(resultado.next()) {
                profesores.add(getProfesor(resultado));
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return profesores;
    }
    
}
