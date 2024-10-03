package Excepciones;

public class InvalidClubIdException extends ClubException {
    public InvalidClubIdException() {
        super("El ID del club no es válido. Debe ser un número positivo y único.");
    }
}

