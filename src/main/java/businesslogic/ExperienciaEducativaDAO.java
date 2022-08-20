package businesslogic;

import dataaccess.ConexionBD;
import domain.ExperienciaEducativa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author liu
 */
public class ExperienciaEducativaDAO implements IExperienciaEducativaDAO {

    @Override
    public ExperienciaEducativa obtenerExperienciaEducativa(ExperienciaEducativa experienciaEducativa) throws SQLException {
        ExperienciaEducativa experienciaEducativaObtenida = new ExperienciaEducativa();
        String consulta =
            "SELECT * FROM ExperienciaEducativa\n" +
            "WHERE nrc = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, experienciaEducativa.getNrc());
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()) {
                experienciaEducativaObtenida = getExperienciaEducativa(resultado);
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return experienciaEducativaObtenida;
    }

    private ExperienciaEducativa getExperienciaEducativa(ResultSet resultado) throws SQLException {
        String nrc;
        String nombre;
        
        nrc = resultado.getString("nrc");
        nombre = resultado.getString("nombre");
        
        ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
        experienciaEducativa.setNrc(nrc);
        experienciaEducativa.setNombre(nombre);
        
        return experienciaEducativa;
    }
    
}
