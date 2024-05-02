package co.luisfbejaranob.financial.entity.api.infrastructure.transaction.persistence.postgresql;

import co.luisfbejaranob.financial.entity.api.domain.transaction.Transaction;

public final class TransactionMother
{
    private TransactionMother()
    {}

    public static Transaction getPayloadTransactionSavingsAccount()
    {
        return new Transaction(500000.0, "3312345678", "5312345678", "TRANSFER_BETWEEN_ACCOUNTS");
    }

    public static Transaction getPayloadTransactionCurrentAccount()
    {
        return new Transaction(800000.0, "5312345678", "3312345678", "TRANSFER_BETWEEN_ACCOUNTS");
    }

    public static Transaction getTransactionSavingsAccount()
    {
        return new Transaction(1L, 250000.0, "5312345678", "5312345678", "CONSIGNMENT");
    }
}
