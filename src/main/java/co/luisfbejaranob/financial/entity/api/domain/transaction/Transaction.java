package co.luisfbejaranob.financial.entity.api.domain.transaction;

public class Transaction
{
    private Long id;

    private String transactionType;

    private Double amount;

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

    public Double getAmount()
    {
        return amount;
    }
    public void setAmount(Double amount)
    {
        this.amount = amount;
    }
}
