package co.luisfbejaranob.financial.entity.api.infrastructure.product.persistence.postgresql;

import co.luisfbejaranob.financial.entity.api.domain.product.Product;
import co.luisfbejaranob.financial.entity.api.shared.enums.AccountTypeEnum;

public final class ProductMother
{
    private ProductMother()
    {}

    public static Product getPayloadProductSavingsAccount()
    {
        return new Product("SAVINGS_ACCOUNT", buildAccountNumber(AccountTypeEnum.SAVINGS_ACCOUNT), "ACTIVE",1000000.0, true);
    }

    public static Product getPayloadProductCurrentAccount()
    {
        return new Product("CURRENT_ACCOUNT", buildAccountNumber(AccountTypeEnum.CURRENT_ACCOUNT), "ACTIVE",1000000.0, true);
    }

    public static Product getProductSavingsAccountActive()
    {
        return new Product(1L, "SAVINGS_ACCOUNT", "5312345678", "ACTIVE",1000000.0, true);
    }

    public static Product getProductSavingsAccountInactive()
    {
        return new Product(1L, "SAVINGS_ACCOUNT", "5312345678", "INACTIVE",0.0, true);
    }

    public static Product getProductSavingsAccountCancelled()
    {
        return new Product(1L, "SAVINGS_ACCOUNT", "5312345678", "CANCELLED",0.0, true);
    }

    public static Product getProductCurrentAccountActive()
    {
        return new Product(2L, "CURRENT_ACCOUNT", "3312345678", "ACTIVE",1000000.0, true);
    }

    public static Product getProductCurrentAccountInactive()
    {
        return new Product(2L, "CURRENT_ACCOUNT", "3312345678", "INACTIVE",0.0, true);
    }

    public static Product getProductCurrentAccountCancelled()
    {
        return new Product(2L, "CURRENT_ACCOUNT", "3312345678", "CANCELLED",0.0, true);
    }



    private static String buildAccountNumber(AccountTypeEnum accountType)
    {
        String numberRandom = String.valueOf((int)(Math.random()*99999999+1));
        String prefixSavingsAccount = "53";
        String prefixCurrentAccount = "33";
        String accountNumber = "N/A";

        if(accountType.name().equals("SAVINGS_ACCOUNT"))
        {
            accountNumber = prefixSavingsAccount + numberRandom;
        }
        if(accountType.name().equals("CURRENT_ACCOUNT"))
        {
            accountNumber = prefixCurrentAccount + numberRandom;
        }

        return accountNumber;
    }
}
