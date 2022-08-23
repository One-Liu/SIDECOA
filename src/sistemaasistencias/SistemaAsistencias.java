package sistemaasistencias;

import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author liu
 */
public class SistemaAsistencias extends Application {
    
    @Override
    public void start(Stage escenario) throws IOException {
        FXMLLoader cargadorFXML = new FXMLLoader(this.getClass().getResource("GUILogin.fxml"));
        Scene escena = new Scene((Parent) cargadorFXML.load());
        GUILoginControlador controladorGUI = cargadorFXML.getController();
        controladorGUI.cargarCamposGUI();
        escenario.setTitle("Inicio de sesión");
        escenario.setScene(escena);
        escenario.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
