package co.luisfbejaranob.financial.entity.api.domain.client;

import co.luisfbejaranob.financial.entity.api.shared.infrastructure.exception.CustomError;

public interface ClientErrors
{
  final class ClientNotFound extends CustomError
  {
    public ClientNotFound(Long id)
    {
      super("101", "Client with ID '%s' not found".formatted(id));
    }

    public ClientNotFound(String number)
    {
      super("106", "Client with identification number '%s' not found".formatted(number));
    }
  }

  final class ClientAlreadyExists extends CustomError
  {
    public ClientAlreadyExists(Long id)
    {
      super("102", "Client with ID '%s' already exists".formatted(id));
    }
  }

  final class ClientInsufficientAge extends CustomError
  {
    public ClientInsufficientAge()
    {
      super("107", "Client is a minor and cannot register");
    }
  }
}
