package co.luisfbejaranob.financial.entity.api.infrastructure.transaction.persistence.postgresql;

import co.luisfbejaranob.financial.entity.api.domain.transaction.Transaction;

public final class TransactionMappers
{
    private TransactionMappers()
    {}

    static Transaction fromRaw(TransactionEntity entity)
    {
        Transaction transaction = new Transaction();
        transaction.setId(entity.getId());
        transaction.setTransactionType(entity.getTransactionType());
        transaction.setOriginAccount(entity.getOriginAccount());
        transaction.setDestinationAccount(entity.getDestinationAccount());
        transaction.setAmount(entity.getAmount());

        return transaction;
    }

    static TransactionEntity from(Transaction transaction)
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
