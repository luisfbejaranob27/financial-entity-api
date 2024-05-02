package co.luisfbejaranob.financial.entity.api.domain.transaction;

import java.util.Objects;

public class Transaction
{
    private Long id;

    private String transactionType;

    private String originAccount;

    private String destinationAccount;

    private Double amount;

    public Transaction()
    {}

    public Transaction(
            Double amount,
            String destinationAccount,
            String originAccount,
            String transactionType
    )
    {
        this.amount = amount;
        this.destinationAccount = destinationAccount;
        this.originAccount = originAccount;
        this.transactionType = transactionType;
    }

    public Transaction(
            Long id,
            Double amount,
            String destinationAccount,
            String originAccount,
            String transactionType
    )
    {
        this.id = id;
        this.amount = amount;
        this.destinationAccount = destinationAccount;
        this.originAccount = originAccount;
        this.transactionType = transactionType;
    }

    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }

    public String getTransactionType()
    {
        return transactionType;
    }
    public void setTransactionType(String transactionType)
    {
        this.transactionType = transactionType;
    }

    public String getOriginAccount()
    {
        return originAccount;
    }
    public void setOriginAccount(String originAccount)
    {
        this.originAccount = originAccount;
    }

    public String getDestinationAccount()
    {
        return destinationAccount;
    }
    public void setDestinationAccount(String destinationAccount)
    {
        this.destinationAccount = destinationAccount;
    }

    public Double getAmount()
    {
        return amount;
    }
    public void setAmount(Double amount)
    {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(transactionType, that.transactionType) &&
                Objects.equals(originAccount, that.originAccount) &&
                Objects.equals(destinationAccount, that.destinationAccount) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                transactionType,
                originAccount,
                destinationAccount,
                amount
        );
    }
}
