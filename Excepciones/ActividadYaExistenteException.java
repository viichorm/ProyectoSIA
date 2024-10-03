package Excepciones;

public class ActividadYaExistenteException extends Exception {
    public ActividadYaExistenteException(String mensaje) {
        super(mensaje);
    }
}
