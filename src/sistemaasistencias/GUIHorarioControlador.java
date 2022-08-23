package sistemaasistencias;

import businesslogic.AsistenciaDAO;
import domain.Asistencia;
import domain.Estudiante;
import domain.Horario;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author liu
 */
public class GUIHorarioControlador {

    @FXML
    private TableView<Horario> tblHorario;
    @FXML
    private TableColumn<Horario, String> clmNRC;
    @FXML
    private TableColumn<Horario, String> clmEE;
    @FXML
    private TableColumn<Horario, String> clmSalon;
    @FXML
    private TableColumn<Horario, String> clmDia;
    @FXML
    private TableColumn<Horario, String> clmInicia;
    @FXML
    private TableColumn<Horario, String> clmTermina;
    
    private Estudiante estudiante = new Estudiante();
    
    private ObservableList<Horario> horarioEstudiante = FXCollections.observableArrayList();
    
    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
    
    public void cargarCamposGUI() throws SQLException {
        AsistenciaDAO asistenciaDAO = new AsistenciaDAO();
        Asistencia asistencia = new Asistencia();
        asistencia.setEstudiante(this.estudiante);
        ObservableList<Asistencia> asistenciasEstudiante = FXCollections.observableArrayList();
        asistenciasEstudiante.addAll(asistenciaDAO.obtenerHorarioEstudiante(asistencia));
        
        Horario horario;
        for(Asistencia asistenciaRegistrada : asistenciasEstudiante) {
            horario = asistenciaRegistrada.getHorario();
            this.horarioEstudiante.add(horario);
        }
        
        this.clmNRC.setCellValueFactory(new PropertyValueFactory("nrc"));
        this.clmEE.setCellValueFactory(new PropertyValueFactory("nombreEE"));
        this.clmSalon.setCellValueFactory(new PropertyValueFactory("salon"));
        this.clmDia.setCellValueFactory(new PropertyValueFactory("stringDia"));
        this.clmInicia.setCellValueFactory(new PropertyValueFactory("horaInicio"));
        this.clmTermina.setCellValueFactory(new PropertyValueFactory("horaTermino"));
        
        this.tblHorario.setItems(this.horarioEstudiante);
    }
    
    @FXML
    private void clicAceptar(ActionEvent event) {
        UtilidadVentana.cerrarVentana(event);
    }
    
}
