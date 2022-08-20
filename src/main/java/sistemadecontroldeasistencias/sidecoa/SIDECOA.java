package sidecoa;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author liu
 */
public class SIDECOA extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("GUILogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Inicio de sesión");
        stage.setScene(scene);
        stage.show();
    }
}
