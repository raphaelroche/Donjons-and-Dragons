package exception;

public class ArmureException extends RuntimeException {
  public ArmureException() {
    super("Votre attaque n'a pas transpersé l'armure de votre cible !");
  }
}
