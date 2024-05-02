package co.luisfbejaranob.financial.entity.api.infrastructure.product.persistence.postgresql;

import co.luisfbejaranob.financial.entity.api.domain.product.Product;
import co.luisfbejaranob.financial.entity.api.domain.product.ProductErrors.ProductNotFound;
import co.luisfbejaranob.financial.entity.api.domain.product.ProductRepository;
import co.luisfbejaranob.financial.entity.api.shared.enums.AccountTypeEnum;
import co.luisfbejaranob.financial.entity.api.shared.enums.StatusEnum;
import org.springframework.stereotype.Repository;

import static co.luisfbejaranob.financial.entity.api.shared.mappers.ProductMappers.*;

@Repository
public class ProductRepositoryImpl implements ProductRepository
{
    private final JpaProductRepository jpaRepository;

    public ProductRepositoryImpl(JpaProductRepository jpaRepository)
    {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Product findById(Long id) {
        return entityFromRaw(
                jpaRepository.findById(id)
                        .orElseThrow(() -> new ProductNotFound(id))
        );
    }

    @Override
    public Product findByAccountNumber(String accountNumber) {
        return entityFromRaw(
                jpaRepository.findByAccountNumber(accountNumber)
                        .orElseThrow(() -> new ProductNotFound(accountNumber))
        );
    }

    @Override
    public Product create(Product product) {
        return entityFromRaw(
                jpaRepository.save(rawFromEntity(product))
        );
    }

    @Override
    public Product update(Product product)
    {
        ProductEntity productDb = jpaRepository.findById(product.getId())
                .orElseThrow(() -> new ProductNotFound(product.getId()));

        return entityFromRaw(
                jpaRepository.save(updateValues(productDb, product))
        );
    }

    private ProductEntity updateValues(ProductEntity productDb, Product product)
    {
        if(product.getAccountType() != null)
        {
            productDb.setAccountType(AccountTypeEnum.valueOf(product.getAccountType()));
        }
        if(product.getAccountNumber() != null)
        {
            productDb.setAccountNumber(product.getAccountNumber());
        }
        if(product.getStatus() != null)
        {
            productDb.setStatus(StatusEnum.valueOf(product.getStatus()));
        }
        if(product.getBalance() != null)
        {
            productDb.setBalance(product.getBalance());
        }
        if(product.getGmfExempt() != null)
        {
            productDb.setGmfExempt(product.getGmfExempt());
        }

        return productDb;
    }

    @Override
    public void deleteById(Long id)
    {
        boolean exists = jpaRepository.existsById(id);

        if(exists)
        {
            jpaRepository.deleteById(id);
        }
        else
        {
            throw new ProductNotFound(id);
        }
    }
}
