package co.luisfbejaranob.financial.entity.api.domain.product;

import java.util.Objects;

public class Product
{
    private Long id;

    private String accountType;

    private String accountNumber;

    private String status;

    private Double balance;

    private Boolean gmfExempt;

    public Product()
    {}

    public Product(
            String accountType,
            String accountNumber,
            String status,
            Double balance,
            Boolean gmfExempt
    )
    {
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.status = status;
        this.balance = balance;
        this.gmfExempt = gmfExempt;
    }

    public Product(
            Long id,
            String accountType,
            String accountNumber,
            String status,
            Double balance,
            Boolean gmfExempt
    )
    {
        this.id = id;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.status = status;
        this.balance = balance;
        this.gmfExempt = gmfExempt;
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
