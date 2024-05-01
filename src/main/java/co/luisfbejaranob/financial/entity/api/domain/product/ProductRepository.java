package co.luisfbejaranob.financial.entity.api.domain.product;

public interface ProductRepository
{
    Product findById(Long id);

    Product create(Product product);

    Product update(Product product);

    void deleteById(Long id);
}