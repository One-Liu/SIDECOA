package sistemaasistencias;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 * @author liu
 */
public class UtilidadVentana {

    public static void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipoAlerta){
        Alert alerta = new Alert(tipoAlerta);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public static void mensajePerdidaDeConexion(){
        UtilidadVentana.mostrarAlerta(
                "Pérdida de conexión",
                "No se pudo conectar con la base de datos. "+
                        "Por favor, inténtelo más tarde.",
                Alert.AlertType.ERROR
        );
    }

    public static void mensajeErrorAlCargarLaInformacionDeLaVentana(){
        UtilidadVentana.mostrarAlerta("Error de sistema", "Hubo un error "
                + "al cargar la información. Por favor, inténtelo más tarde",Alert.AlertType.ERROR);
    }

    public static void cerrarVentana(ActionEvent evento) {
        Node fuente = (Node) evento.getSource();
        Stage escenario = (Stage) fuente.getScene().getWindow();
        escenario.close();
    }
}
