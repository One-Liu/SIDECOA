package businesslogic;

import dataaccess.ConexionBD;
import domain.ExperienciaEducativa;
import domain.Profesor;
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
public class ExperienciaEducativaDAO implements IExperienciaEducativaDAO {

    @Override
    public ExperienciaEducativa obtenerExperienciaEducativa(ExperienciaEducativa experienciaEducativa) throws SQLException {
        ExperienciaEducativa experienciaEducativaObtenida = new ExperienciaEducativa();
        String consulta =
            "SELECT * FROM ExperienciaEducativa WHERE nrc = ?";
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

    @Override
    public List<ExperienciaEducativa> obtenerExperienciasEducativasSinProfesorAsignado() throws SQLException {
        List<ExperienciaEducativa> experienciasEducativas = new ArrayList<>();
        String consulta = "SELECT * FROM ExperienciaEducativa WHERE nrc NOT IN (SELECT nrc FROM EE_Profesor)";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            while(resultado.next()) {
                experienciasEducativas.add(getExperienciaEducativa(resultado));
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return experienciasEducativas;
    }

    @Override
    public List<ExperienciaEducativa> obtenerExperienciasEducativasDeProfesor(Profesor profesor) throws SQLException {
        List<ExperienciaEducativa> experienciasEducativas = new ArrayList<>();
        String consulta = "SELECT EE.* FROM ExperienciaEducativa EE INNER JOIN EE_Profesor EP ON EP.nrc = EE.nrc WHERE EP.numPersonal = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, profesor.getNumPersonal());
            ResultSet resultado = sentencia.executeQuery();
            while(resultado.next()) {
                experienciasEducativas.add(getExperienciaEducativa(resultado));
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return experienciasEducativas;
    }
    
}
