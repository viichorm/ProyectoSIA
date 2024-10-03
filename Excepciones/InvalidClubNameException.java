package Excepciones;

public class InvalidClubNameException extends ClubException {
    public InvalidClubNameException() {
        super("El nombre del club no es válido. No puede estar vacío o contener solo espacios.");
    }
}
