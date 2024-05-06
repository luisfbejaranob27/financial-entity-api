package co.luisfbejaranob.financial.entity.api.domain.product;

public interface ProductRepository
{
    Product findById(Long id);

    Product findByAccountNumber(String accountNumber);

    Product create(Product product, String identificationNumber);

    Product update(Product product);

    void deleteById(Long id);
}
