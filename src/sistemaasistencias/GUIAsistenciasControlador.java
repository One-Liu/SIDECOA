package sistemaasistencias;

import businesslogic.AsistenciaDAO;
import businesslogic.ExperienciaEducativaDAO;
import domain.Asistencia;
import domain.DatosGlobalesDeSesion;
import domain.Estudiante;
import domain.ExperienciaEducativa;
import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 *
 * @author liu
 */
public class GUIAsistenciasControlador {

    @FXML
    private ComboBox<ExperienciaEducativa> cbExperienciaEducativa;
    @FXML
    private TableView<Estudiante> tblAsistencias;
    @FXML
    private TableColumn<Estudiante, String> clmNombre;
    @FXML
    private TableColumn<Estudiante, String> clmApellidoPaterno;
    @FXML
    private TableColumn<Estudiante, String> clmApellidoMaterno;
    @FXML
    private TableColumn<Estudiante, String> clmCorreoInstitucional;

    private ObservableList<ExperienciaEducativa> experienciasEducativas = FXCollections.observableArrayList();
    private ObservableList<Estudiante> estudiantes = FXCollections.observableArrayList();

    public void cargarCamposGUI() throws SQLException {
        ExperienciaEducativaDAO experienciaEducativaDAO = new ExperienciaEducativaDAO();
        this.experienciasEducativas.addAll(experienciaEducativaDAO.obtenerExperienciasEducativas());

        this.cbExperienciaEducativa.setItems(experienciasEducativas);
        this.cbExperienciaEducativa.getSelectionModel().selectFirst();
        this.cbExperienciaEducativa.setConverter(new StringConverter<ExperienciaEducativa>() {
            @Override
            public String toString(ExperienciaEducativa experienciaEducativa) {
                return experienciaEducativa == null ? null : "(" + experienciaEducativa.getNrc() + ") " + experienciaEducativa.getNombre();
            }

            @Override
            public ExperienciaEducativa fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        this.clmNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.clmApellidoPaterno.setCellValueFactory(new PropertyValueFactory("apellidoPaterno"));
        this.clmApellidoMaterno.setCellValueFactory(new PropertyValueFactory("apellidoMaterno"));
        this.clmCorreoInstitucional.setCellValueFactory(new PropertyValueFactory("correoInstitucional"));
    }

    @FXML
    private void clicSalir(ActionEvent evento) {
        FXMLLoader cargadorFXML = new FXMLLoader(this.getClass().getResource("GUILogin.fxml"));
        try {
            Scene escena = new Scene((Parent) cargadorFXML.load());
            GUILoginControlador controladorGUI = cargadorFXML.getController();
            controladorGUI.cargarCamposGUI();
            Stage escenario = new Stage();
            escenario.setTitle("Inicio de sesión");
            escenario.setScene(escena);
            escenario.show();

            DatosGlobalesDeSesion.getDatosGlobalesDeSesion().setEstudiante(null);
            DatosGlobalesDeSesion.getDatosGlobalesDeSesion().setProfesor(null);
            UtilidadVentana.cerrarVentana(evento);
        } catch(IOException excepcionIO) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
    }

    @FXML
    private void clicConsultarHorarioClases(ActionEvent evento) {
        Estudiante estudianteSeleccionado = tblAsistencias.getSelectionModel().getSelectedItem();
        if(estudianteSeleccionado != null) {
            FXMLLoader cargadorFXML = new FXMLLoader(this.getClass().getResource("GUIHorario.fxml"));
            try {
                Scene escena = new Scene((Parent) cargadorFXML.load());
                GUIHorarioControlador controladorGUI = cargadorFXML.getController();
                controladorGUI.setEstudiante(estudianteSeleccionado);
                controladorGUI.cargarCamposGUI();
                Stage escenario = new Stage();
                escenario.setTitle("Horario");
                escenario.setScene(escena);
                escenario.showAndWait();
            } catch(IOException excepcionIO) {
                UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
            } catch(SQLException excepcionSQL) {
                UtilidadVentana.mensajePerdidaDeConexion();
            }
        } else {
            UtilidadVentana.mostrarAlerta(
                "Selección de estudiante", 
                "Para consultar un horario primero debe seleccionar un estudiante", 
                Alert.AlertType.WARNING
            );
        }
    }

    @FXML
    private void clicBuscar(ActionEvent evento) {
        AsistenciaDAO asistenciaDAO = new AsistenciaDAO();
        ExperienciaEducativa experienciaEducativaSeleccionada = this.cbExperienciaEducativa.getSelectionModel().getSelectedItem();
        Asistencia asistenciaBusqueda = new Asistencia();
        asistenciaBusqueda.getHorario().setExperienciaEducativa(experienciaEducativaSeleccionada);

        ObservableList<Asistencia> asistencias = FXCollections.observableArrayList();
        Estudiante visualizacionEstudiante;

        try {
            asistencias.addAll(asistenciaDAO.obtenerAsistenciasPorEE(asistenciaBusqueda));

            for(Asistencia asistencia : asistencias) {
                visualizacionEstudiante = asistencia.getEstudiante();
                this.estudiantes.add(visualizacionEstudiante);
            }

            if(!asistencias.isEmpty()) {
                this.tblAsistencias.setItems(estudiantes);
            } else {
                this.tblAsistencias.getItems().clear();
            }
        } catch(SQLException excepcionSQL) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
    }
}
