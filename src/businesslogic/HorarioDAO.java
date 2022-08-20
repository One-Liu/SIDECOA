package businesslogic;

import dataaccess.ConexionBD;
import domain.DiaDeLaSemana;
import domain.ExperienciaEducativa;
import domain.Horario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

/**
 *
 * @author liu
 */
public class HorarioDAO implements IHorarioDAO {
    
    @Override
    public Horario obtenerHorario(Horario horario) throws SQLException {
        Horario horarioObtenido = new Horario();
        String consulta =
            "SELECT * FROM Horario\n" +
            "WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, horario.getId());
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()) {
                horarioObtenido = getHorario(resultado);
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return horarioObtenido;
    }

    private Horario getHorario(ResultSet resultado) throws SQLException {
        int id;
        Time horaInicio;
        Time horaTermino;
        String salon;
        String nrc;
        int idDiaDeLaSemana;
        ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
        ExperienciaEducativaDAO experienciaEducativaDAO = new ExperienciaEducativaDAO();
        DiaDeLaSemana diaDeLaSemana = new DiaDeLaSemana();
        DiaDeLaSemanaDAO diaDeLaSemanaDAO = new DiaDeLaSemanaDAO();
        
        id = resultado.getInt("id");
        horaInicio = resultado.getTime("horaInicio");
        horaTermino = resultado.getTime("horaTermino");
        salon = resultado.getString("salon");
        nrc = resultado.getString("nrc");
        idDiaDeLaSemana = resultado.getInt("idDiaDeLaSemana");
        experienciaEducativa.setNrc(nrc);
        diaDeLaSemana.setId(idDiaDeLaSemana);
        experienciaEducativa = experienciaEducativaDAO.obtenerExperienciaEducativa(experienciaEducativa);
        diaDeLaSemana = diaDeLaSemanaDAO.obtenerDiaDeLaSemana(diaDeLaSemana);
        
        Horario horario = new Horario();
        horario.setId(id);
        horario.setHoraInicio(horaInicio);
        horario.setHoraTermino(horaTermino);
        horario.setSalon(salon);
        horario.setExperienciaEducativa(experienciaEducativa);
        horario.setDia(diaDeLaSemana);
        
        return horario;
    }
}
