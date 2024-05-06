package co.luisfbejaranob.financial.entity.api.application.usecase;

import co.luisfbejaranob.financial.entity.api.domain.client.Client;
import co.luisfbejaranob.financial.entity.api.domain.client.ClientRepository;
import co.luisfbejaranob.financial.entity.api.domain.product.Product;
import co.luisfbejaranob.financial.entity.api.domain.product.ProductRepository;
import co.luisfbejaranob.financial.entity.api.shared.enums.AccountTypeEnum;
import co.luisfbejaranob.financial.entity.api.shared.enums.StatusEnum;
import org.springframework.stereotype.Service;

@Service
public class ProductUseCase
{
    private final ProductRepository repository;

    private final ClientRepository clientRepository;

    public ProductUseCase(ProductRepository repository, ClientRepository clientRepository)
    {
        this.repository = repository;
        this.clientRepository = clientRepository;
    }

    public Product findById(Long id)
    {
        return repository.findById(id);
    }

    public Product findByAccountNumber(String accountNumber)
    {
        return repository.findByAccountNumber(accountNumber);
    }

    public Product create(Product product, String identificationNumber)
    {
        product.setStatus(StatusEnum.ACTIVE.name());

        if(product.getAccountType().equals(AccountTypeEnum.CURRENT_ACCOUNT.name()))
        {
            product.setAccountNumber(buildAccountNumber(AccountTypeEnum.CURRENT_ACCOUNT));
        }
        if(product.getAccountType().equals(AccountTypeEnum.SAVINGS_ACCOUNT.name()))
        {
            product.setAccountNumber(buildAccountNumber(AccountTypeEnum.SAVINGS_ACCOUNT));
        }

        return repository.create(product, identificationNumber);
    }

    private String buildAccountNumber(AccountTypeEnum accountType)
    {
        String numberRandom = String.valueOf((int)(Math.random()*99999999+1));
        String prefixSavingsAccount = "53";
        String prefixCurrentAccount = "33";
        String accountNumber = "N/A";

        if(accountType.name().equals("CURRENT_ACCOUNT"))
        {
            accountNumber = prefixCurrentAccount + numberRandom;
        }
        if(accountType.name().equals("SAVINGS_ACCOUNT"))
        {
            accountNumber = prefixSavingsAccount + numberRandom;
        }

        return accountNumber;
    }

    public Product update(Product product)
    {
        return repository.update(product);
    }

    public Product cancelled(Long id)
    {
        Product product = findById(id);

        if(product.getBalance().equals(0.0))
        {
            product.setStatus(StatusEnum.CANCELLED.name());
        }

        return repository.update(product);
    }

    public Product inactive(Long id)
    {
        Product product = findById(id);

        product.setStatus(StatusEnum.INACTIVE.name());

        return repository.update(product);
    }
}
