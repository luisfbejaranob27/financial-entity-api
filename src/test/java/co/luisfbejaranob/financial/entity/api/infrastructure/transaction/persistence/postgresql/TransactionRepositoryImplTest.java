package co.luisfbejaranob.financial.entity.api.infrastructure.transaction.persistence.postgresql;

import co.luisfbejaranob.financial.entity.api.domain.transaction.Transaction;
import co.luisfbejaranob.financial.entity.api.domain.transaction.TransactionErrors.TransactionNotFound;
import co.luisfbejaranob.financial.entity.api.domain.transaction.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class TransactionRepositoryImplTest
{
    private TransactionRepository sut;

    @Autowired
    private JpaTransactionRepository repository;

    @BeforeEach
    void setUp()
    {
        sut = new TransactionRepositoryImpl(repository);
    }

    @Test
    void findTransactionByIdFound()
    {
        Transaction transaction = TransactionMother.getTransactionSavingsAccountTransferBetweenAccounts();

        Transaction transactionFound = sut.findById(transaction.getId());

        assertEquals(transaction, transactionFound);
    }

    @Test
    void findTransactionThrowTransactionNotFount()
    {
        Exception exception = assertThrows(TransactionNotFound.class, () ->
                sut.findById(5L));

        String expectedMessage = "Transaction with ID '5' not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createTransaction()
    {
        Transaction transaction = TransactionMother.getTransactionSavingsAccountTransferBetweenAccounts();

        Transaction transactionCreated = sut.create(transaction);

        assertNotNull(transactionCreated.getId());
        assertEquals(transaction.getTransactionType(), transactionCreated.getTransactionType());
        assertEquals(transaction.getOriginAccount(), transactionCreated.getOriginAccount());
        assertEquals(transaction.getDestinationAccount(), transactionCreated.getDestinationAccount());
        assertEquals(transaction.getAmount(), transactionCreated.getAmount());
    }

    @Test
    void updateTransaction()
    {
        Transaction transaction = TransactionMother.getTransactionSavingsAccountTransferBetweenAccounts();
        transaction.setAmount(300000.0);

        Transaction transactionUpdated = sut.update(transaction);

        assertEquals(transaction.getId(), transactionUpdated.getId());
        assertEquals(transaction.getAmount(), transactionUpdated.getAmount());
    }
}