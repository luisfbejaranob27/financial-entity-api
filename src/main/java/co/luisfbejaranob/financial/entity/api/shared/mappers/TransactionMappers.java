package co.luisfbejaranob.financial.entity.api.shared.mappers;

import co.luisfbejaranob.financial.entity.api.domain.transaction.Transaction;
import co.luisfbejaranob.financial.entity.api.infrastructure.transaction.persistence.postgresql.TransactionEntity;

public final class TransactionMappers
{
    private TransactionMappers()
    {}

    public static Transaction entityFromRaw(TransactionEntity entity)
    {
        Transaction transaction = new Transaction();
        transaction.setId(entity.getId());
        transaction.setTransactionType(entity.getTransactionType());
        transaction.setOriginAccount(entity.getOriginAccount());
        transaction.setDestinationAccount(entity.getDestinationAccount());
        transaction.setAmount(entity.getAmount());

        return transaction;
    }

    public static TransactionEntity rawFromEntity(Transaction transaction)
    {
        return TransactionEntity.builder()
                .id(transaction.getId())
                .transactionType(transaction.getTransactionType())
                .originAccount(transaction.getOriginAccount())
                .destinationAccount(transaction.getDestinationAccount())
                .amount(transaction.getAmount())
                .build();
    }
}
