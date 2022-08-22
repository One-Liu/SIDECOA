package businesslogic;

import dataaccess.ConexionBD;
import domain.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author liu
 */
public class UsuarioDAO implements IUsuarioDAO {

    @Override
    public Usuario obtenerUsuario(Usuario usuario) throws SQLException {
        Usuario usuarioObtenido = new Usuario();
        String consulta = 
            "SELECT * FROM Usuario WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, usuario.getId());
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()) {
                usuarioObtenido = getUsuario(resultado);
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return usuarioObtenido;
    }

    private Usuario getUsuario(ResultSet resultado) throws SQLException {
        int id;
        String correoInstitucional;
        String contrasenia;
        
        id = resultado.getInt("id");
        correoInstitucional = resultado.getString("correoInstitucional");
        contrasenia = resultado.getString("contrasenia");
        
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setCorreoInstitucional(correoInstitucional);
        usuario.setContrasenia(contrasenia);
        
        return usuario;
    }

    @Override
    public Usuario obtenerUsuarioQueIniciaSesion(Usuario usuario) throws SQLException {
        Usuario usuarioObtenido = new Usuario();
        String consulta = 
            "SELECT * FROM Usuario WHERE correoInstitucional = ? AND contrasenia = sha2(?,256)";
        ConexionBD baseDeDatos = new ConexionBD();
        
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, usuario.getCorreoInstitucional());
            sentencia.setString(2, usuario.getContrasenia());
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()) {
                usuarioObtenido = getUsuario(resultado);
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return usuarioObtenido;
    }

    @Override
    public int agregarUsuario(Usuario usuario) throws SQLException {
        int idUsuario = 0;
        String consulta = "INSERT INTO Usuario VALUES(NULL,?,?)";
        ConexionBD baseDeDatos = new ConexionBD();
        
        try (Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, usuario.getCorreoInstitucional());
            sentencia.setString(2, usuario.getContrasenia());
            sentencia.executeUpdate();
            ResultSet resultado = sentencia.getGeneratedKeys();
            
            if (resultado.next()) {
                idUsuario = resultado.getInt(1);
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return idUsuario;
    }

    @Override
    public boolean validarUsuarioRegistrado(Usuario usuario) throws SQLException {
        boolean usuarioRegistrado = false;
        String consulta = "SELECT * FROM Usuario WHERE correoInstitucional = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, usuario.getCorreoInstitucional());
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.next()) {
                usuarioRegistrado = true;
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return usuarioRegistrado;
    }
    
}
