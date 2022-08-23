package sistemaasistencias;

import businesslogic.EE_ProfesorDAO;
import businesslogic.ExperienciaEducativaDAO;
import businesslogic.ProfesorDAO;
import domain.EE_Profesor;
import domain.ExperienciaEducativa;
import domain.Profesor;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

/**
 *
 * @author liu
 */
public class GUIAsignarExperienciaEducativaAProfesorControlador {

    @FXML
    private ComboBox<Profesor> cbProfesor;
    @FXML
    private ComboBox<ExperienciaEducativa> cbEE;

    private ObservableList<Profesor> profesores = FXCollections.observableArrayList();
    private ObservableList<ExperienciaEducativa> experienciasEducativas = FXCollections.observableArrayList();

    public void cargarCamposGUI() throws SQLException {
        ProfesorDAO profesorDAO = new ProfesorDAO();
        ExperienciaEducativaDAO experienciaEducativaDAO = new ExperienciaEducativaDAO();
        this.profesores.addAll(profesorDAO.obtenerProfesores());
        this.experienciasEducativas.addAll(experienciaEducativaDAO.obtenerExperienciasEducativas());

        this.cbProfesor.setItems(profesores);
        this.cbProfesor.getSelectionModel().selectFirst();
        this.cbProfesor.setConverter(new StringConverter<Profesor>() {
            @Override
            public String toString(Profesor profesor) {
                return profesor == null ? null : "(" + profesor.getNumPersonal() + ") " + profesor.getNombreCompleto();
            }

            @Override
            public Profesor fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

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
    }

    @FXML
    private void clicCancelar(ActionEvent evento) {
        UtilidadVentana.cerrarVentana(evento);
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
            Profesor profesor = this.cbProfesor.getSelectionModel().getSelectedItem();
            ee_Profesor.setExperienciaEducativa(experienciaEducativa);
            ee_Profesor.setProfesor(profesor);
            EE_ProfesorDAO ee_ProfesorDAO = new EE_ProfesorDAO();

            try {
                ee_ProfesorDAO.asignarEE_Profesor(ee_Profesor);
                UtilidadVentana.mostrarAlerta(
                    "Asignación exitosa",
                    "El profesor se ha asignado exitosamente a la experiencia educativa",
                    Alert.AlertType.INFORMATION
                );
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