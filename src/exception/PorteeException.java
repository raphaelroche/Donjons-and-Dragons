package exception;

public class PorteeException extends RuntimeException {
    public PorteeException() {
        super("Votre cible est trop loin pour la portée de votre arme");
    }
}
