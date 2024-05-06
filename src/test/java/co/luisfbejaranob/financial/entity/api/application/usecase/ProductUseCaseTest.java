package co.luisfbejaranob.financial.entity.api.application.usecase;

import co.luisfbejaranob.financial.entity.api.domain.product.ProductRepository;
import co.luisfbejaranob.financial.entity.api.infrastructure.client.persistence.postgresql.ClientMother;
import co.luisfbejaranob.financial.entity.api.infrastructure.product.persistence.postgresql.ProductMother;
import co.luisfbejaranob.financial.entity.api.shared.enums.StatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ProductUseCaseTest
{
    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductUseCase sut;

    @Test
    void findById()
    {
        var reference = ProductMother.getProductCurrentAccountActive();
        given(repository.findById(reference.getId())).willReturn(reference);

        var product = sut.findById(reference.getId());

        assertEquals(reference, product);

        then(repository).should().findById(reference.getId());
    }

    @Test
    void findByAccountNumber()
    {
        var reference = ProductMother.getProductCurrentAccountActive();
        given(repository.findByAccountNumber(reference.getAccountNumber())).willReturn(reference);
        
        var product = sut.findByAccountNumber(reference.getAccountNumber());
        
        assertEquals(reference, product);
        
        then(repository).should().findByAccountNumber(reference.getAccountNumber());
    }

    @Test
    void createCurrentAccount()
    {
        var payload = ProductMother.getPayloadProductCurrentAccountActive();
        var clientIdentificationNumber = ClientMother.getClient().getIdentificationNumber();
        var reference = ProductMother.getProductCurrentAccountActive();
        given(repository.create(payload, clientIdentificationNumber)).willReturn(reference);

        var product = sut.create(payload, clientIdentificationNumber);

        assertEquals(reference, product);
        assertTrue(product.getAccountNumber().startsWith("33"));

        then(repository).should().create(payload, clientIdentificationNumber);
    }

    @Test
    void createSavingsAccount()
    {
        var payload = ProductMother.getPayloadProductSavingsAccountActive();
        var clientIdentificationNumber = ClientMother.getClient().getIdentificationNumber();
        var reference = ProductMother.getProductSavingsAccountActive();
        given(repository.create(payload, clientIdentificationNumber)).willReturn(reference);

        var product = sut.create(payload, clientIdentificationNumber);

        assertEquals(reference, product);
        assertTrue(product.getAccountNumber().startsWith("53"));

        then(repository).should().create(payload, clientIdentificationNumber);
    }

    @Test
    void update()
    {
        var product = ProductMother.getProductCurrentAccountActive();
        product.setGmfExempt(false);
        given(repository.update(product)).willReturn(product);

        var productUpdate = sut.update(product);

        assertEquals(product, productUpdate);

        then(repository).should().update(product);
    }

    @Test
    void cancelled()
    {
        var reference = ProductMother.getProductCurrentAccountActiveBalanceZero();
        given(repository.findById(reference.getId())).willReturn(reference);
        reference.setStatus(StatusEnum.CANCELLED.name());
        given(repository.update(reference)).willReturn(reference);

        var product = sut.cancelled(reference.getId());

        assertEquals(product.getStatus(), StatusEnum.CANCELLED.name());

        then(repository).should().findById(reference.getId());
        then(repository).should().update(reference);
    }

    @Test
    void inactive()
    {
        var reference = ProductMother.getProductCurrentAccountActiveBalanceZero();
        given(repository.findById(reference.getId())).willReturn(reference);
        reference.setStatus(StatusEnum.INACTIVE.name());
        given(repository.update(reference)).willReturn(reference);

        var product = sut.inactive(reference.getId());

        assertEquals(product.getStatus(), StatusEnum.INACTIVE.name());

        then(repository).should().findById(reference.getId());
        then(repository).should().update(reference);
    }
}