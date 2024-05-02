package co.luisfbejaranob.financial.entity.api.domain.product;

import co.luisfbejaranob.financial.entity.api.shared.infrastructure.exception.CustomError;

public interface ProductErrors
{
  final class ProductNotFound extends CustomError
  {
    public ProductNotFound(String id)
    {
      super("103", "Product with ID '%s' not found".formatted(id));
    }
  }

  final class ProductAlreadyExists extends CustomError
  {
    public ProductAlreadyExists(String id)
    {
      super("104", "Product with ID '%s' already exists".formatted(id));
    }
  }
}
