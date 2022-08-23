package sistemaasistencias;

import businesslogic.EstudianteDAO;
import businesslogic.ProfesorDAO;
import businesslogic.UsuarioDAO;
import domain.Estudiante;
import domain.Profesor;
import domain.Usuario;
import java.sql.SQLException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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
    private void clicCancelar(ActionEvent event) {
        UtilidadVentana.cerrarVentana(event);
    }

    private boolean hayCamposLlenos() {
        boolean camposLlenos = true;
        if(tfNombre.getText().trim().isEmpty()
            || tfApellidoPaterno.getText().trim().isEmpty()
            || tfApellidoMaterno.getText().trim().isEmpty()
            || tfCorreoInstitucional.getText().trim().isEmpty()
            || tfMatriculaNumPersonal.getText().trim().isEmpty()) {
            UtilidadVentana.mostrarAlerta(
                "Campos vacíos",
                "No puede haber campos vacíos",
                Alert.AlertType.WARNING);
            camposLlenos = false;
        }
        return camposLlenos;
    }
    
    private boolean correoInstitucionalValido() {
        boolean correoInstitucionalValido = false;
        String correoInstitucional = this.tfCorreoInstitucional.getText().replaceAll("\\s+", "").trim();
        
        if(correoInstitucional.endsWith("@uv.mx") || correoInstitucional.endsWith("@estudiantes.uv.mx")) {
            correoInstitucionalValido = true;
        } else {
            UtilidadVentana.mostrarAlerta(
                "Correo institucional", 
                "El correo ingresado no es un correo válido", 
                Alert.AlertType.WARNING
            );
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
                    "El correo ingresado ya está en uso", 
                    Alert.AlertType.ERROR);
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
                }
                break;
            case("Profesor"):
                if(matriculaNumPersonal.length() == 5
                    && matriculaNumPersonal.chars().allMatch(Character::isDigit)) {
                    matriculaNumPersonalValido = true;
                }
                break;
        }
        
        return matriculaNumPersonalValido;
    }
    
    @FXML
    private void clicRegistrar(ActionEvent event) {
        if(hayCamposLlenos() && correoInstitucionalValido() && !correoEstaRegistrado() && matriculaNumPersonalValido() && !matriculaNumPersonalEstaRegistrado()) {
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
                            "La contraseña del estudiante es " + usuarioNuevo.getContrasenia() + ".\n" +
                                "Solicite al usuario cambiarla cuando ingrese al sistema.", 
                            Alert.AlertType.INFORMATION);
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
                            "La contraseña del profesor es " + usuarioNuevo.getContrasenia() + ".\n" +
                                "Solicite al usuario cambiarla cuando ingrese al sistema.", 
                            Alert.AlertType.INFORMATION);
                    } catch(SQLException excepcionSQL) {
                        UtilidadVentana.mensajePerdidaDeConexion();
                    }
                    break;
            }
        }
    }
    
}
