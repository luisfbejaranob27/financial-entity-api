package co.luisfbejaranob.financial.entity.api.infrastructure.product.persistence.postgresql;

import co.luisfbejaranob.financial.entity.api.domain.product.Product;
import co.luisfbejaranob.financial.entity.api.shared.enums.AccountTypeEnum;
import co.luisfbejaranob.financial.entity.api.shared.enums.StatusEnum;

public final class ProductMappers
{
    private ProductMappers()
    {}

    static Product fromRaw(ProductEntity entity)
    {
        Product product = new Product();
        product.setId(entity.getId());
        product.setAccountType(entity.getAccountType().name());
        product.setAccountNumber(entity.getAccountNumber());
        product.setStatus(entity.getStatus().name());
        product.setBalance(entity.getBalance());
        product.setGmfExempt(entity.getGmfExempt());

        return product;
    }

    static ProductEntity from(Product product)
    {
        return ProductEntity.builder()
                .id(product.getId())
                .accountType(AccountTypeEnum.valueOf(product.getAccountType()))
                .accountNumber(product.getAccountNumber())
                .status(StatusEnum.valueOf(product.getStatus()))
                .balance(product.getBalance())
                .gmfExempt(product.getGmfExempt())
                .build();
    }
}
