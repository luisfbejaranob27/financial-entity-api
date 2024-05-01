package co.luisfbejaranob.financial.entity.api.domain.transaction;

import co.luisfbejaranob.financial.entity.api.shared.core.CustomError;

public interface TransactionErrors
{
  final class TransactionNotFound extends CustomError
  {
    public TransactionNotFound(String id)
    {
      super("105", "Transaction with ID '%s' not found".formatted(id));
    }
  }
}
