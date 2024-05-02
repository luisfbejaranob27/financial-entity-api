package co.luisfbejaranob.financial.entity.api.application.usecase;

import co.luisfbejaranob.financial.entity.api.domain.product.Product;
import co.luisfbejaranob.financial.entity.api.domain.product.ProductRepository;
import co.luisfbejaranob.financial.entity.api.domain.transaction.Transaction;
import co.luisfbejaranob.financial.entity.api.domain.transaction.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionUseCase
{
    private final TransactionRepository transactionRepository;

    private final ProductRepository productRepository;

    public TransactionUseCase(
            TransactionRepository transactionRepository,
            ProductRepository productRepository
    )
    {
        this.transactionRepository = transactionRepository;
        this.productRepository = productRepository;
    }

    public Transaction findById(Long id)
    {
        return transactionRepository.findById(id);
    }

    public Transaction create(Transaction transaction)
    {
        Product originAccount = productRepository.findByAccountNumber(transaction.getOriginAccount());
        Product destinationAccount = productRepository.findByAccountNumber(transaction.getDestinationAccount());

        if (!originAccount.getAccountNumber().equals(destinationAccount.getAccountNumber())) {
            originAccount.debit(transaction.getAmount());
            productRepository.update(originAccount);

        }
        destinationAccount.credit(transaction.getAmount());
        productRepository.update(destinationAccount);

        return transactionRepository.create(transaction);
    }
}
