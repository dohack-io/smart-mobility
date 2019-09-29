package io.dohack.smartmobility.vrr;

public class VrrException extends RuntimeException {
  public VrrException() {
    super();
  }

  public VrrException(String msg, Exception e) {
    super(msg, e);
  }
}
