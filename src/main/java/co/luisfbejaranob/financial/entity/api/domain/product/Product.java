package co.luisfbejaranob.financial.entity.api.domain.product;

import co.luisfbejaranob.financial.entity.api.domain.client.Client;

import java.util.Objects;

public class Product
{
    private Long id;

    private String accountType;

    private String accountNumber;

    private String status;

    private Double balance;

    private Boolean gmfExempt;

    private Client client;

    public Product()
    {}

    public Product(
            String accountType,
            String accountNumber,
            String status,
            Double balance,
            Boolean gmfExempt,
            Client client
    )
    {
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.status = status;
        this.balance = balance;
        this.gmfExempt = gmfExempt;
        this.client = client;
    }

    public Product(
            Long id,
            String accountType,
            String accountNumber,
            String status,
            Double balance,
            Boolean gmfExempt,
            Client client
    )
    {
        this.id = id;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.status = status;
        this.balance = balance;
        this.gmfExempt = gmfExempt;
        this.client = client;
    }

    public void credit(Double amount)
    {
        this.balance += amount;
    }

    public void debit(Double amount)
    {
        this.balance -= amount;
    }

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
        return gmfExempt;
    }
    public void setGmfExempt(Boolean gmfExempt)
    {
        this.gmfExempt = gmfExempt;
    }

    public Client getClient()
    {
        return client;
    }
    public void setClient(Client client)
    {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(accountType, product.accountType) &&
                Objects.equals(accountNumber, product.accountNumber) &&
                Objects.equals(status, product.status) &&
                Objects.equals(balance, product.balance) &&
                Objects.equals(gmfExempt, product.gmfExempt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                accountType,
                accountNumber,
                status,
                balance,
                gmfExempt
        );
    }
}
