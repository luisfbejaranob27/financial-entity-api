package co.luisfbejaranob.financial.entity.api.shared.mappers;

import co.luisfbejaranob.financial.entity.api.domain.client.Client;
import co.luisfbejaranob.financial.entity.api.domain.product.Product;
import co.luisfbejaranob.financial.entity.api.infrastructure.client.persistence.postgresql.ClientEntity;
import co.luisfbejaranob.financial.entity.api.infrastructure.product.persistence.postgresql.ProductEntity;
import co.luisfbejaranob.financial.entity.api.shared.enums.AccountTypeEnum;
import co.luisfbejaranob.financial.entity.api.shared.enums.StatusEnum;

import static co.luisfbejaranob.financial.entity.api.shared.mappers.ClientMappers.rawFromClientEntity;

public final class ProductMappers
{
    private ProductMappers()
    {}

    public static Product entityFromRawPayload(ProductEntity entity)
    {
        Product product = new Product();
        product.setAccountType(entity.getAccountType().name());
        product.setBalance(entity.getBalance());
        product.setGmfExempt(entity.getGmfExempt());

        return product;
    }

    public static Product entityFromRaw(ProductEntity entity)
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

    public static ProductEntity rawFromEntity(Product product, Client client)
    {
        return ProductEntity.builder()
                .id(product.getId())
                .accountType(AccountTypeEnum.valueOf(product.getAccountType()))
                .accountNumber(product.getAccountNumber())
                .status(StatusEnum.valueOf(product.getStatus()))
                .balance(product.getBalance())
                .gmfExempt(product.getGmfExempt())
                .client(rawFromClientEntity(client))
                .build();
    }
}
