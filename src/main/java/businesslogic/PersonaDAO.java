package businesslogic;

import dataaccess.ConexionBD;
import domain.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author liu
 */
public class PersonaDAO implements IPersonaDAO {
    
    @Override
    public Persona obtenerPersona(Persona persona) throws SQLException {
        Persona personaObtenida = new Persona();
        String consulta =
            "SELECT * FROM Persona\n" +
            "WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, persona.getIdPersona());
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()) {
                personaObtenida = getPersona(resultado);
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return personaObtenida;
    }

    private Persona getPersona(ResultSet resultado) throws SQLException {
        int id;
        String nombre;
        String apellidoPaterno;
        String apellidoMaterno;
        
        id = resultado.getInt("id");
        nombre = resultado.getString("nombre");
        apellidoPaterno = resultado.getString("apellidoPaterno");
        apellidoMaterno = resultado.getString("apellidoMaterno");
        
        Persona persona = new Persona();
        persona.setIdPersona(id);
        persona.setNombre(nombre);
        persona.setApellidoPaterno(apellidoPaterno);
        persona.setApellidoMaterno(apellidoMaterno);
        
        return persona;
    }

    @Override
    public int agregarPersona(Persona persona) throws SQLException {
        ConexionBD baseDeDatos = new ConexionBD();
        int idPersona = 0;
        try (Connection conexion = baseDeDatos.abrirConexion()) {
            String consulta = "INSERT INTO Persona VALUES(NULL,?,?,?)";
            PreparedStatement sentencia = conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, persona.getNombre());
            sentencia.setString(2, persona.getApellidoPaterno());
            sentencia.setString(3, persona.getApellidoMaterno());
            sentencia.executeUpdate();
            ResultSet resultado = sentencia.getGeneratedKeys();
            
            if (resultado.next()) {
                idPersona = resultado.getInt(1);
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return idPersona;
    }
}
