package businesslogic;

import dataaccess.ConexionBD;
import domain.EE_Profesor;
import domain.ExperienciaEducativa;
import domain.Profesor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author liu
 */
public class EE_ProfesorDAO implements IEE_ProfesorDAO {

    @Override
    public EE_Profesor obtenerEE_Profesor(EE_Profesor experienciaEducativa_Profesor) throws SQLException {
        EE_Profesor experienciaEducativa_ProfesorObtenido = new EE_Profesor();
        String consulta =
            "SELECT * FROM EE_Profesor WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, experienciaEducativa_Profesor.getId());
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()) {
                experienciaEducativa_ProfesorObtenido = getEE_Profesor(resultado);
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return experienciaEducativa_ProfesorObtenido;
    }

    private EE_Profesor getEE_Profesor(ResultSet resultado) throws SQLException {
        int id;
        String nrc;
        String numPersonal;
        ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
        ExperienciaEducativaDAO experienciaEducativaDAO = new ExperienciaEducativaDAO();
        Profesor profesor = new Profesor();
        ProfesorDAO profesorDAO = new ProfesorDAO();
        
        id = resultado.getInt("id");
        nrc = resultado.getString("nrc");
        numPersonal = resultado.getString("numPersonal");
        experienciaEducativa.setNrc(nrc);
        profesor.setNumPersonal(numPersonal);
        experienciaEducativa = experienciaEducativaDAO.obtenerExperienciaEducativa(experienciaEducativa);
        profesor = profesorDAO.obtenerProfesor(profesor);
        
        EE_Profesor experienciaEducativa_Profesor = new EE_Profesor();
        experienciaEducativa_Profesor.setId(id);
        experienciaEducativa_Profesor.setExperienciaEducativa(experienciaEducativa);
        experienciaEducativa_Profesor.setProfesor(profesor);
        
        return experienciaEducativa_Profesor;
    }
    
}
