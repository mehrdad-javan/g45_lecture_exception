package se.lexicon;

public class InsufficientFoundsException extends Exception {

  private Integer errorCode;

  public InsufficientFoundsException(String message) {
    super(message);
  }

 public Integer getErrorCode() {
    return errorCode;
  }

}
