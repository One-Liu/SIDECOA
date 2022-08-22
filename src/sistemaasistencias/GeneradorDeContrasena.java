package sistemaasistencias;

/**
 *
 * @author liu
 */
public class GeneradorDeContrasena {

    private static final String NUMEROS = "0123456789";
    private static final String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String MINUSCULAS=  "abcdefghijklmnopqrstuvwxyz";
    private static final String ESPECIALES = "ñÑ#%&=*+-/_";

    public static String getContrasena() {
        return getContrasena(8);
    }

    private static String getContrasena(int longitud) {
        return getContrasena(NUMEROS + MAYUSCULAS + MINUSCULAS + ESPECIALES, longitud);
    }

    private static String getContrasena(String caracteres, int longitud) {
        String contrasena = "";

        for(int i = 0; i < longitud; i++) {
            contrasena += (caracteres.charAt((int) (Math.random() * caracteres.length())));
        }

        return contrasena;
    }
}