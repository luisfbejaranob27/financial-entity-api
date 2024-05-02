package co.luisfbejaranob.financial.entity.api.infrastructure.transaction.persistence.postgresql;

import co.luisfbejaranob.financial.entity.api.domain.transaction.Transaction;
import co.luisfbejaranob.financial.entity.api.domain.transaction.TransactionErrors.TransactionNotFound;
import co.luisfbejaranob.financial.entity.api.domain.transaction.TransactionRepository;
import org.springframework.stereotype.Repository;

import static co.luisfbejaranob.financial.entity.api.shared.mappers.TransactionMappers.*;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository
{
    private final JpaTransactionRepository jpaRepository;

    public TransactionRepositoryImpl(JpaTransactionRepository jpaRepository)
    {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Transaction findById(Long id) {
        return entityFromRaw(
                jpaRepository.findById(id)
                        .orElseThrow(() -> new TransactionNotFound(id))
        );
    }

    @Override
    public Transaction create(Transaction transaction) {
        return entityFromRaw(
                jpaRepository.save(rawFromEntity(transaction))
        );
    }

    @Override
    public Transaction update(Transaction transaction) {

        TransactionEntity transactionDb = jpaRepository.findById(transaction.getId())
                .orElseThrow(() -> new TransactionNotFound(transaction.getId()));

        return entityFromRaw(
                jpaRepository.save(updateValues(transactionDb, transaction))
        );
    }

    private TransactionEntity updateValues(TransactionEntity transactionDb, Transaction transaction)
    {
        if(transaction.getTransactionType() != null)
        {
            transactionDb.setTransactionType(transaction.getTransactionType());
        }
        if(transaction.getOriginAccount() != null)
        {
            transactionDb.setOriginAccount(transaction.getOriginAccount());
        }
        if(transaction.getDestinationAccount() != null)
        {
            transactionDb.setDestinationAccount(transaction.getDestinationAccount());
        }
        if(transaction.getAmount() != null)
        {
            transactionDb.setAmount(transaction.getAmount());
        }

        return transactionDb;
    }
}
