package co.luisfbejaranob.financial.entity.api.domain.transaction;

public interface TransactionRepository
{
    Transaction findById(Long id);

    Transaction create(Transaction transaction);

    Transaction update(Transaction transaction);
}
