package co.luisfbejaranob.financial.entity.api.domain.transaction;

import co.luisfbejaranob.financial.entity.api.shared.infrastructure.exception.CustomError;

public interface TransactionErrors
{
  final class TransactionNotFound extends CustomError
  {
    public TransactionNotFound(Long id)
    {
      super("105", "Transaction with ID '%s' not found".formatted(id));
    }
  }
}
