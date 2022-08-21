package businesslogic;

import dataaccess.ConexionBD;
import domain.DiaDeLaSemana;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author liu
 */
public class DiaDeLaSemanaDAO implements IDiaDeLaSemanaDAO {

    @Override
    public DiaDeLaSemana obtenerDiaDeLaSemana(DiaDeLaSemana diaDeLaSemana) throws SQLException {
        DiaDeLaSemana diaDeLaSemanaObtenido = new DiaDeLaSemana();
        String consulta =
            "SELECT * FROM DiaDeLaSemana WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareCall(consulta);
            sentencia.setInt(1, diaDeLaSemana.getId());
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()) {
                diaDeLaSemanaObtenido = getDiaDeLaSemana(resultado);
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return diaDeLaSemanaObtenido;
    }

    private DiaDeLaSemana getDiaDeLaSemana(ResultSet resultado) throws SQLException {
        int id;
        String nombre;
        
        id = resultado.getInt("id");
        nombre = resultado.getString("nombre");
        
        DiaDeLaSemana diaDeLaSemana = new DiaDeLaSemana();
        diaDeLaSemana.setId(id);
        diaDeLaSemana.setNombre(nombre);
        
        return diaDeLaSemana;
    }
    
}
