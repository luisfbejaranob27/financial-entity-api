package co.luisfbejaranob.financial.entity.api.infrastructure.transaction.persistence.postgresql;

import co.luisfbejaranob.financial.entity.api.domain.transaction.Transaction;

public final class TransactionMother
{
    private TransactionMother()
    {}

    public static Transaction getPayloadTransactionSavingsAccountTransferBetweenAccounts()
    {
        return new Transaction(500000.0, "3312345678", "5312345678", "TRANSFER_BETWEEN_ACCOUNTS");
    }

    public static Transaction getTransactionSavingsAccountTransferBetweenAccounts()
    {
        return new Transaction(1L, 500000.0, "3312345678", "5312345678", "TRANSFER_BETWEEN_ACCOUNTS");
    }

    public static Transaction getPayloadTransactionCurrentAccountTransferBetweenAccounts()
    {
        return new Transaction(800000.0, "5312345678", "3312345678", "TRANSFER_BETWEEN_ACCOUNTS");
    }

    public static Transaction getransactionCurrentAccountTransferBetweenAccounts()
    {
        return new Transaction(2L, 800000.0, "5312345678", "3312345678", "TRANSFER_BETWEEN_ACCOUNTS");
    }

    public static Transaction getPayloadTransactionSavingsAccountConsignment()
    {
        return new Transaction(250000.0, "5312345678", "5312345678", "CONSIGNMENT");
    }

    public static Transaction getTransactionSavingsAccountConsignment()
    {
        return new Transaction(3L, 250000.0, "5312345678", "5312345678", "CONSIGNMENT");
    }

    public static Transaction getPayloadTransactionCurrentAccountConsignment()
    {
        return new Transaction(250000.0, "3312345678", "3312345678", "CONSIGNMENT");
    }

    public static Transaction getTransactionCurrentAccountConsignment()
    {
        return new Transaction(4L, 250000.0, "3312345678", "3312345678", "CONSIGNMENT");
    }

    public static Transaction getPayloadTransactionSavingsAccountWithdrawal()
    {
        return new Transaction(100000.0, "", "3312345678", "WITHDRAWAL");
    }

    public static Transaction getTransactionSavingsAccountWithdrawal()
    {
        return new Transaction(5L,100000.0, "", "3312345678", "WITHDRAWAL");
    }

    public static Transaction getPayloadTransactionCurrentAccountWithdrawal()
    {
        return new Transaction(6L,100000.0, "", "3312345678", "WITHDRAWAL");
    }

    public static Transaction getTransactionCurrentAccountWithdrawal()
    {
        return new Transaction(6L,100000.0, "", "3312345678", "WITHDRAWAL");
    }
}
