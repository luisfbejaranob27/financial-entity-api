package co.luisfbejaranob.financial.entity.api.domain.product;

public class Product
{
    private Long id;

    private String accountType;

    private String accountNumber;

    private String status;

    private Double balance;

    private Boolean isGmfExempt;

    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }

    public String getAccountType()
    {
        return accountType;
    }
    public void setAccountType(String accountType)
    {
        this.accountType = accountType;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public Double getBalance()
    {
        return balance;
    }
    public void setBalance(Double balance)
    {
        this.balance = balance;
    }

    public Boolean getGmfExempt()
    {
        return isGmfExempt;
    }
    public void setGmfExempt(Boolean gmfExempt)
    {
        isGmfExempt = gmfExempt;
    }
}
