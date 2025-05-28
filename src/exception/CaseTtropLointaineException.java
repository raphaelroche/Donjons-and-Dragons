package exception;

public class CaseTtropLointaineException extends RuntimeException {
    public CaseTtropLointaineException() {
      super("Cette case est trop lointaine pour vous y deplacer !");
    }
}
