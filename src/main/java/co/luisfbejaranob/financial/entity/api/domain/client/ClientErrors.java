package co.luisfbejaranob.financial.entity.api.domain.client;

import co.luisfbejaranob.financial.entity.api.shared.core.CustomError;

public interface ClientErrors
{
  final class ClientNotFound extends CustomError
  {
    public ClientNotFound(String id)
    {
      super("101", "Client with ID '%s' not found".formatted(id));
    }
  }

  final class ClientAlreadyExists extends CustomError
  {
    public ClientAlreadyExists(String id)
    {
      super("102", "Client with ID '%s' already exists".formatted(id));
    }
  }
}
