package sistemaasistencias;

import businesslogic.EstudianteDAO;
import businesslogic.ProfesorDAO;
import businesslogic.UsuarioDAO;
import domain.Estudiante;
import domain.Profesor;
import domain.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author liu
 */
public class GUIRegistroDeUsuarioControlador {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private TextField tfCorreoInstitucional;
    @FXML
    private TextField tfMatriculaNumPersonal;
    @FXML
    private ComboBox<String> cbTipoUsuario;

    public void cargarCamposGUI() {
        tfNombre.lengthProperty().addListener(new ChangeListener<Number>() {
            int tamanoMaximo = 50;
            
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number valorAnterior, Number valorActual) {
                if (valorActual.intValue() > valorAnterior.intValue()) {
                    if (tfNombre.getText().length() >= tamanoMaximo) {
                        tfNombre.setText(tfNombre.getText().substring(0, tamanoMaximo));
                    }
                }
            }
        });
        tfApellidoPaterno.lengthProperty().addListener(new ChangeListener<Number>() {
            int tamanoMaximo = 50;
            
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number valorAnterior, Number valorActual) {
                if (valorActual.intValue() > valorAnterior.intValue()) {
                    if (tfApellidoPaterno.getText().length() >= tamanoMaximo) {
                        tfApellidoPaterno.setText(tfApellidoPaterno.getText().substring(0, tamanoMaximo));
                    }
                }
            }
        });
        tfApellidoMaterno.lengthProperty().addListener(new ChangeListener<Number>() {
            int tamanoMaximo = 50;
            
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number valorAnterior, Number valorActual) {
                if (valorActual.intValue() > valorAnterior.intValue()) {
                    if (tfApellidoMaterno.getText().length() >= tamanoMaximo) {
                        tfApellidoMaterno.setText(tfApellidoMaterno.getText().substring(0, tamanoMaximo));
                    }
                }
            }
        });
        tfCorreoInstitucional.lengthProperty().addListener(new ChangeListener<Number>() {
            int tamanoMaximo = 30;
            
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number valorAnterior, Number valorActual) {
                if (valorActual.intValue() > valorAnterior.intValue()) {
                    if (tfCorreoInstitucional.getText().length() >= tamanoMaximo) {
                        tfCorreoInstitucional.setText(tfCorreoInstitucional.getText().substring(0, tamanoMaximo));
                    }
                }
            }
        });
        tfMatriculaNumPersonal.lengthProperty().addListener(new ChangeListener<Number>() {
            int tamanoMaximo = 9;
            
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number valorAnterior, Number valorActual) {
                if (valorActual.intValue() > valorAnterior.intValue()) {
                    if (tfMatriculaNumPersonal.getText().length() >= tamanoMaximo) {
                        tfMatriculaNumPersonal.setText(tfMatriculaNumPersonal.getText().substring(0, tamanoMaximo));
                    }
                }
            }
        });
        cbTipoUsuario.getItems().addAll("Estudiante","Profesor");
        cbTipoUsuario.getSelectionModel().selectFirst();
    }
    
    @FXML
    private void clicRegresar(ActionEvent event) {
        FXMLLoader cargadorFXML = new FXMLLoader(this.getClass().getResource("GUILogin.fxml"));
        try {
            Scene escena = new Scene((Parent) cargadorFXML.load());
            GUILoginControlador controladorGUI = cargadorFXML.getController();
            controladorGUI.cargarCamposGUI();
            Stage escenario = new Stage();
            escenario.setTitle("Inicio de sesi??n");
            escenario.setScene(escena);
            escenario.show();
            UtilidadVentana.cerrarVentana(event);
        } catch(IOException excepcionIO) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
    }

    private boolean hayCamposLlenos() {
        boolean camposLlenos = true;
        if(tfNombre.getText().trim().isEmpty()
            || tfApellidoPaterno.getText().trim().isEmpty()
            || tfApellidoMaterno.getText().trim().isEmpty()
            || tfCorreoInstitucional.getText().trim().isEmpty()
            || tfMatriculaNumPersonal.getText().trim().isEmpty()) {
            UtilidadVentana.mostrarAlerta(
                "Campos vac??os",
                "No puede haber campos vac??os",
                Alert.AlertType.WARNING);
            camposLlenos = false;
        }
        return camposLlenos;
    }
    
    private boolean correoInstitucionalValido() {
        boolean correoInstitucionalValido = false;
        String tipoUsuario = this.cbTipoUsuario.getSelectionModel().getSelectedItem();
        String correoInstitucional = this.tfCorreoInstitucional.getText().replaceAll("\\s+", "").trim();
        
        switch(tipoUsuario) {
            case ("Estudiante"):
                if(correoInstitucional.endsWith("@estudiantes.uv.mx")) {
                    correoInstitucionalValido = true;
                } else {
                    UtilidadVentana.mostrarAlerta(
                        "Correo institucional inv??lido", 
                        "El correo institucional ingresado es inv??lido", 
                        Alert.AlertType.WARNING
                    );
                }
                break;

            case ("Profesor"):
                if(correoInstitucional.endsWith("@uv.mx")) {
                    correoInstitucionalValido = true;
                } else {
                    UtilidadVentana.mostrarAlerta(
                        "Correo institucional inv??lido", 
                        "El correo institucional ingresado es inv??lido", 
                        Alert.AlertType.WARNING
                    );
                }
                break;
        }
        
        return correoInstitucionalValido;
    }
    
    private boolean correoEstaRegistrado() {
        boolean correoRegistradoAnteriormente = false;
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        String correoInstitucional = this.tfCorreoInstitucional.getText().replaceAll("\\s+", "").trim();
        
        Usuario usuarioNuevo = new Usuario(
            correoInstitucional,
            ""
        );
        
        try {
            if(usuarioDAO.validarUsuarioRegistrado(usuarioNuevo)) {
                UtilidadVentana.mostrarAlerta(
                    "Usuario no registrado", 
                    "El correo ingresado ya est?? en uso", 
                    Alert.AlertType.WARNING
                );
                correoRegistradoAnteriormente = true;
            }
        } catch(SQLException excepcionSQL) {
            UtilidadVentana.mensajePerdidaDeConexion();
            UtilidadVentana.cerrarVentana(new ActionEvent());
        }
        
        return correoRegistradoAnteriormente;
    }
    
    private boolean matriculaNumPersonalEstaRegistrado() {
        boolean matriculaNumPersonalRegistrado = false;
        String tipoUsuario = this.cbTipoUsuario.getSelectionModel().getSelectedItem();
        String matriculaNumPersonal = this.tfMatriculaNumPersonal.getText().toUpperCase().replaceAll("\\s+", " ").trim();
        EstudianteDAO estudianteDAO = new EstudianteDAO();
        ProfesorDAO profesorDAO = new ProfesorDAO();
        
        switch(tipoUsuario) {
                case("Estudiante"):
                    Estudiante estudianteNuevo = new Estudiante();
                    estudianteNuevo.setMatricula(matriculaNumPersonal);
                    Estudiante estudianteObtenido = new Estudiante();
                    try {
                        estudianteObtenido = estudianteDAO.obtenerEstudiante(estudianteNuevo);
                    } catch(SQLException excepcionSQL) {
                        UtilidadVentana.mensajePerdidaDeConexion();
                    }
                    if(!estudianteObtenido.equals(new Estudiante())) {
                        UtilidadVentana.mostrarAlerta(
                            "Matricula inv??lida", 
                            "La matricula ingresada ya est?? en uso", 
                            Alert.AlertType.WARNING
                        );
                        matriculaNumPersonalRegistrado = true;
                    }
                    break;
                    
                case("Profesor"):
                    Profesor profesorNuevo = new Profesor();
                    profesorNuevo.setNumPersonal(matriculaNumPersonal);
                    Profesor profesorObtenido = new Profesor();
                    try {
                        profesorObtenido = profesorDAO.obtenerProfesor(profesorNuevo);
                    } catch(SQLException excepcionSQL) {
                        UtilidadVentana.mensajePerdidaDeConexion();
                    }
                    if(!profesorObtenido.equals(new Profesor())) {
                        UtilidadVentana.mostrarAlerta(
                            "N??mero de personal inv??lido", 
                            "El n??mero de personal ingresado ya est?? en uso", 
                            Alert.AlertType.WARNING
                        );
                        matriculaNumPersonalRegistrado = true;
                    }
                    break;
            }
        
        return matriculaNumPersonalRegistrado;
    }
    
    private boolean matriculaNumPersonalValido() {
        boolean matriculaNumPersonalValido = false;
        String tipoUsuario = this.cbTipoUsuario.getSelectionModel().getSelectedItem();
        String matriculaNumPersonal = this.tfMatriculaNumPersonal.getText().toUpperCase().replaceAll("\\s+", " ").trim();
        
        switch(tipoUsuario) {
            case("Estudiante"):
                String numerosMatricula = matriculaNumPersonal.substring(1);
                
                if(matriculaNumPersonal.length() == 9
                    && matriculaNumPersonal.startsWith("S")
                    && numerosMatricula.chars().allMatch(Character::isDigit)) {
                    matriculaNumPersonalValido = true;
                } else {
                    UtilidadVentana.mostrarAlerta(
                        "Matricula inv??lida", 
                        "La matricula ingresada no es v??lida", 
                        Alert.AlertType.WARNING
                    );
                }
                break;
            case("Profesor"):
                if(matriculaNumPersonal.length() == 4
                    && matriculaNumPersonal.chars().allMatch(Character::isDigit)) {
                    matriculaNumPersonalValido = true;
                } else {
                    UtilidadVentana.mostrarAlerta(
                        "Numero de personal inv??lido", 
                        "El n??mero de personal ingresado no es v??lido", 
                        Alert.AlertType.WARNING
                    );
                }
                break;
        }
        
        return matriculaNumPersonalValido;
    }
    
    private void limpiarCampos() {
        this.tfNombre.clear();
        this.tfApellidoPaterno.clear();
        this.tfApellidoMaterno.clear();
        this.tfCorreoInstitucional.clear();
        this.tfMatriculaNumPersonal.clear();
    }
    
    @FXML
    private void clicRegistrar(ActionEvent event) {
        if(hayCamposLlenos()
            && correoInstitucionalValido()
            && !correoEstaRegistrado()
            && matriculaNumPersonalValido()
            && !matriculaNumPersonalEstaRegistrado()) {
            String nombre = this.tfNombre.getText().toUpperCase().replaceAll("\\s+", " ").trim();
            String apellidoPaterno = this.tfApellidoPaterno.getText().toUpperCase().replaceAll("\\s+", " ").trim();
            String apellidoMaterno = this.tfApellidoMaterno.getText().toUpperCase().replaceAll("\\s+", " ").trim();
            String correoInstitucional = this.tfCorreoInstitucional.getText().replaceAll("\\s+", " ").trim();
            String matriculaNumPersonal = this.tfMatriculaNumPersonal.getText().toUpperCase().replaceAll("\\s+", " ").trim();
            String tipoUsuario = this.cbTipoUsuario.getSelectionModel().getSelectedItem();
            
            Usuario usuarioNuevo = new Usuario(
                correoInstitucional,
                GeneradorDeContrasena.getContrasena()
            );
            
            switch(tipoUsuario) {
                case("Estudiante"):
                    EstudianteDAO estudianteDAO = new EstudianteDAO();
                    Estudiante estudianteNuevo = new Estudiante(
                        matriculaNumPersonal,
                        0, 
                        nombre,
                        apellidoPaterno,
                        apellidoMaterno,
                        usuarioNuevo
                    );
                    
                    try {
                        estudianteDAO.agregarEstudiante(estudianteNuevo);
                        UtilidadVentana.mostrarAlerta(
                            "Estudiante registrado", 
                            "La contrase??a del estudiante es " + usuarioNuevo.getContrasenia() + ".\n" +
                                "Solicite al usuario cambiarla cuando ingrese al sistema.", 
                            Alert.AlertType.INFORMATION
                        );
                    } catch(SQLException excepcionSQL) {
                        UtilidadVentana.mensajePerdidaDeConexion();
                    }
                    break;
                    
                case("Profesor"):
                    ProfesorDAO profesorDAO = new ProfesorDAO();
                    Profesor profesorNuevo = new Profesor(
                        matriculaNumPersonal, 
                        0, 
                        nombre, 
                        apellidoPaterno, 
                        apellidoMaterno, 
                        usuarioNuevo
                    );
                    
                    try {
                        profesorDAO.agregarProfesor(profesorNuevo);
                        UtilidadVentana.mostrarAlerta(
                            "Profesor registrado", 
                            "La contrase??a del profesor es " + usuarioNuevo.getContrasenia() + ".\n" +
                                "Solicite al usuario cambiarla cuando ingrese al sistema.", 
                            Alert.AlertType.INFORMATION
                        );
                    } catch(SQLException excepcionSQL) {
                        UtilidadVentana.mensajePerdidaDeConexion();
                    }
                    break;
            }
            
            limpiarCampos();
        }
    }
    
}
