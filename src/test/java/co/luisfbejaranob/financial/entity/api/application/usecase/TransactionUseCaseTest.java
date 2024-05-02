package co.luisfbejaranob.financial.entity.api.application.usecase;

import co.luisfbejaranob.financial.entity.api.domain.product.ProductRepository;
import co.luisfbejaranob.financial.entity.api.domain.transaction.TransactionRepository;
import co.luisfbejaranob.financial.entity.api.infrastructure.product.persistence.postgresql.ProductMother;
import co.luisfbejaranob.financial.entity.api.infrastructure.transaction.persistence.postgresql.TransactionMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class TransactionUseCaseTest
{
    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private TransactionUseCase sut;

    @Test
    void findById()
    {
        var reference = TransactionMother.getTransactionCurrentAccountWithdrawal();
        given(transactionRepository.findById(reference.getId())).willReturn(reference);

        var transaction = sut.findById(reference.getId());

        assertEquals(reference, transaction);

        then(transactionRepository).should().findById(reference.getId());
    }

    @Test
    void createTransactionSavingsAccountTransferBetweenAccounts()
    {
        var payload = TransactionMother.getPayloadTransactionSavingsAccountTransferBetweenAccounts();
        var reference = TransactionMother.getTransactionSavingsAccountTransferBetweenAccounts();

        var originAccount = ProductMother.getProductSavingsAccountActive();
        given(productRepository.findByAccountNumber(payload.getOriginAccount())).willReturn(originAccount);

        var destinationAccount = ProductMother.getProductCurrentAccountActive();
        given(productRepository.findByAccountNumber(payload.getOriginAccount())).willReturn(destinationAccount);

        given(transactionRepository.create(payload)).willReturn(reference);

        var transaction = sut.create(payload);

        assertEquals(reference, transaction);

        then(productRepository).should().findByAccountNumber(payload.getOriginAccount());
        then(productRepository).should().findByAccountNumber(payload.getDestinationAccount());
        then(transactionRepository).should().create(payload);
    }

    @Test
    void createTransactionSavingsAccountConsignment()
    {
        var payload = TransactionMother.getPayloadTransactionSavingsAccountConsignment();
        var reference = TransactionMother.getTransactionSavingsAccountConsignment();

        var originAccount = ProductMother.getProductSavingsAccountActive();
        given(productRepository.findByAccountNumber(payload.getOriginAccount())).willReturn(originAccount);

        given(transactionRepository.create(payload)).willReturn(reference);

        var transaction = sut.create(payload);

        assertEquals(reference, transaction);

        then(productRepository).should().findByAccountNumber(payload.getOriginAccount());
        then(transactionRepository).should().create(payload);
    }

    @Test
    void createTransactionSavingsAccountWithdrawal()
    {
        var payload = TransactionMother.getPayloadTransactionSavingsAccountWithdrawal();
        var reference = TransactionMother.getTransactionSavingsAccountWithdrawal();

        var originAccount = ProductMother.getProductSavingsAccountActive();
        given(productRepository.findByAccountNumber(payload.getOriginAccount())).willReturn(originAccount);

        given(transactionRepository.create(payload)).willReturn(reference);

        var transaction = sut.create(payload);

        assertEquals(reference, transaction);

        then(productRepository).should().findByAccountNumber(payload.getOriginAccount());
        then(transactionRepository).should().create(payload);
    }
}