package sistemaasistencias;

import businesslogic.EE_ProfesorDAO;
import businesslogic.ExperienciaEducativaDAO;
import domain.DatosGlobalesDeSesion;
import domain.EE_Profesor;
import domain.ExperienciaEducativa;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.StringConverter;

/**
 *
 * @author liu
 */
public class GUIAsignarExperienciaEducativaAProfesorControlador {

    @FXML
    private Label lblProfesor;
    @FXML
    private ComboBox<ExperienciaEducativa> cbEE;

    private ObservableList<ExperienciaEducativa> experienciasEducativas = FXCollections.observableArrayList();

    public void setExperienciasEducativas(List<ExperienciaEducativa> experienciasEducativasSinProfesorAsignado) {
        this.experienciasEducativas.addAll(experienciasEducativasSinProfesorAsignado);
    }
    
    public void cargarCamposGUI() throws SQLException {
        this.cbEE.setItems(experienciasEducativas);
        this.cbEE.getSelectionModel().selectFirst();
        this.cbEE.setConverter(new StringConverter<ExperienciaEducativa>() {
            @Override
            public String toString(ExperienciaEducativa experienciaEducativa) {
                return experienciaEducativa == null ? null : "(" + experienciaEducativa.getNrc() + ") " + experienciaEducativa.getNombre();
            }

            @Override
            public ExperienciaEducativa fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        this.lblProfesor.setText(DatosGlobalesDeSesion.getDatosGlobalesDeSesion().getProfesor().getNombreCompleto());
    }

    private boolean eeDisponible() {
        boolean eeDisponible = false;
        EE_ProfesorDAO ee_ProfesorDAO = new EE_ProfesorDAO();

        EE_Profesor ee_Profesor = new EE_Profesor();
        ee_Profesor.setExperienciaEducativa(this.cbEE.getSelectionModel().getSelectedItem());

        try {
            if(!ee_ProfesorDAO.eeAsignadaAProfesor(ee_Profesor)) {
                eeDisponible = true;
            }
        } catch(SQLException exceptionSQL) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
        return eeDisponible;
    }

    @FXML
    private void clicAsignar(ActionEvent evento) {
        if(eeDisponible()) {
            EE_Profesor ee_Profesor = new EE_Profesor();
            ExperienciaEducativa experienciaEducativa = this.cbEE.getSelectionModel().getSelectedItem();
            ee_Profesor.setExperienciaEducativa(experienciaEducativa);
            ee_Profesor.setProfesor(DatosGlobalesDeSesion.getDatosGlobalesDeSesion().getProfesor());
            EE_ProfesorDAO ee_ProfesorDAO = new EE_ProfesorDAO();

            try {
                ee_ProfesorDAO.asignarEE_Profesor(ee_Profesor);
                UtilidadVentana.mostrarAlerta(
                    "Asignación exitosa",
                    "El profesor se ha asignado exitosamente a la experiencia educativa",
                    Alert.AlertType.INFORMATION
                );
                UtilidadVentana.cerrarVentana(evento);
            } catch(SQLException excepcionSQL) {
                UtilidadVentana.mensajePerdidaDeConexion();
            }
        } else {
            UtilidadVentana.mostrarAlerta(
                "Profesor no asignado", 
                "La experiencia educativa seleccionada ya tiene un profesor asignado", 
                Alert.AlertType.ERROR
            );
        }
    }
}
