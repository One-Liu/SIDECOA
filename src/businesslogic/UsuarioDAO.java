package businesslogic;

import dataaccess.ConexionBD;
import domain.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    
}
