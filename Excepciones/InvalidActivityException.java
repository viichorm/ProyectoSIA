package Excepciones;

public class InvalidActivityException extends ClubException {
    public InvalidActivityException() {
        super("La actividad ingresada no es válida. Por favor verifique los datos.");
    }
}

