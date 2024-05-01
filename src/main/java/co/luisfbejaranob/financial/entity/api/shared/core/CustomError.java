package co.luisfbejaranob.financial.entity.api.shared.core;

import java.io.Serial;

public abstract class CustomError extends RuntimeException
{
  @Serial private static final long serialVersionUID = 1L;

  private final String code;

  protected CustomError(String code, String message)
  {
    super(message);
    this.code = code;
  }

  protected CustomError(String code, String message, Throwable cause)
  {
    super(message, cause);
    this.code = code;
  }

  public String getCode()
  {
    return code;
  }
}
