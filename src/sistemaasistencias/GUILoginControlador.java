package sistemaasistencias;

import businesslogic.EstudianteDAO;
import businesslogic.ProfesorDAO;
import businesslogic.UsuarioDAO;
import domain.DatosGlobalesDeSesion;
import domain.Estudiante;
import domain.Profesor;
import domain.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author liu
 */
public class GUILoginControlador {
    @FXML
    private TextField tfCorreoInstitucional;
    
    @FXML
    private PasswordField pfContrasena;
    
    private Usuario usuario = new Usuario();

    public boolean validarCamposLlenos() {
        boolean camposLlenos = true;
        if(tfCorreoInstitucional.getText().trim().isEmpty()
            || pfContrasena.getText().trim().isEmpty()) {
            UtilidadVentana.mostrarAlerta(
                "Campos vacíos",
                "No puede haber campos vacíos",
                Alert.AlertType.WARNING);
            camposLlenos = false;
        }
        return camposLlenos;
    }

    private void validarTipoDeUsuario(ActionEvent evento) throws SQLException, IOException {
        if(tfCorreoInstitucional.getText().trim().contains("@estudiantes.uv.mx")) {
            EstudianteDAO estudianteDAO = new EstudianteDAO();
            Estudiante estudiante = new Estudiante();
            estudiante.setUsuario(usuario);
            estudiante = estudianteDAO.obtenerEstudianteQueIniciaSesion(estudiante);
            DatosGlobalesDeSesion.getDatosGlobalesDeSesion().setEstudiante(estudiante);
            System.out.println("S");
/*
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource(""));
            Parent raiz = cargadorFXML.load();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Menú principal");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.show();
            UtilidadVentana.cerrarVentana(evento);
            */
            
        } else if(tfCorreoInstitucional.getText().trim().contains("@uv.mx")) {
            ProfesorDAO profesorDAO = new ProfesorDAO();
            Profesor profesor = new Profesor();
            profesor.setUsuario(usuario);
            profesor = profesorDAO.obtenerProfesorQueIniciaSesion(profesor);
            DatosGlobalesDeSesion.getDatosGlobalesDeSesion().setProfesor(profesor);
            System.out.println("P");
            /*
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource(""));
            Parent raiz = cargadorFXML.load();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Menú principal");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.show();
            UtilidadVentana.cerrarVentana(evento);
*/
        }
    }

    @FXML
    private void clicIniciarSesion(ActionEvent evento) {
        if(validarCamposLlenos()) {
            this.usuario = new Usuario( 
                this.tfCorreoInstitucional.getText(),
                this.pfContrasena.getText()
            );
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            try {
                this.usuario = usuarioDAO.obtenerUsuarioQueIniciaSesion(this.usuario);
                if(this.usuario.getId() == 0) {
                    UtilidadVentana.mostrarAlerta(
                        "Error en el inicio de sesión",
                        "Verifique que el correo y contraseña sean correctos",
                        Alert.AlertType.WARNING
                    );
                } else {
                    validarTipoDeUsuario(evento);
                }
            } catch(SQLException ex) {
                UtilidadVentana.mensajePerdidaDeConexion();
            } catch(IOException excepcionIO) {
                UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
            }
        }
    }
}
