package se.lexicon;

public class DataNotFoundException extends Exception {
  private String paramValue;
  private String message;
  private Integer errorCode;

  public DataNotFoundException(String message, String paramValue) {
    super(message);
    this.message = message;
    this.paramValue = paramValue;
  }

  public DataNotFoundException(String message, Integer errorCode, String paramValue) {
    this(message, paramValue);
    this.errorCode = errorCode;
  }

  // getter methods if needed

 public Integer getErrorCode() {
    return errorCode;
  }

  public String getParamValue() {
    return paramValue;
  }
}
