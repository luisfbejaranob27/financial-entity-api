package co.luisfbejaranob.financial.entity.api.infrastructure.product.persistence.postgresql;

import co.luisfbejaranob.financial.entity.api.domain.client.ClientRepository;
import co.luisfbejaranob.financial.entity.api.domain.product.Product;
import co.luisfbejaranob.financial.entity.api.domain.product.ProductErrors.ProductNotFound;
import co.luisfbejaranob.financial.entity.api.domain.product.ProductRepository;
import co.luisfbejaranob.financial.entity.api.infrastructure.client.persistence.postgresql.ClientMother;
import co.luisfbejaranob.financial.entity.api.infrastructure.client.persistence.postgresql.ClientRepositoryImpl;
import co.luisfbejaranob.financial.entity.api.infrastructure.client.persistence.postgresql.JpaClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryImplTest
{
    private ProductRepository sut;

    @Autowired
    private JpaProductRepository productRepository;

    ClientRepository clientRepository;

    @Autowired
    private JpaClientRepository jpaClientRepository;

    @BeforeEach
    void setUp()
    {
        clientRepository = new ClientRepositoryImpl(jpaClientRepository);
        sut = new ProductRepositoryImpl(productRepository, clientRepository);
    }

    @Test
    void findProductByIdFound()
    {
        Product product = ProductMother.getProductSavingsAccountActive();

        Product productFound = sut.findById(product.getId());

        assertEquals(product, productFound);
    }

    @Test
    void findProductByIdThrowProductNotFound()
    {
        Exception exception = assertThrows(ProductNotFound.class, () ->
                sut.findById(5L));

        String expectedMessage = "Product with ID '5' not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void findProductByAccountNumberThrowProductNotFound()
    {
        Exception exception = assertThrows(ProductNotFound.class, () ->
                sut.findByAccountNumber("5301234567"));

        String expectedMessage = "Product with account number '5301234567' not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createProduct()
    {
        Product product = ProductMother.getPayloadProductCurrentAccountActive();
        String clientIdentificationNumber = ClientMother.getClient3().getIdentificationNumber();

        Product productCreated = sut.create(product, clientIdentificationNumber);

        assertNotNull(productCreated.getId());
        assertEquals(product.getAccountType(), productCreated.getAccountType());
        assertEquals(product.getAccountNumber(), productCreated.getAccountNumber());
        assertEquals(product.getStatus(), productCreated.getStatus());
        assertEquals(product.getBalance(), productCreated.getBalance());
        assertEquals(product.getGmfExempt(), productCreated.getGmfExempt());
    }

    @Test
    void updateProduct()
    {
        Product product = ProductMother.getProductSavingsAccountActive();
        product.setBalance(1200000.0);

        Product productUpdated = sut.update(product);

        assertEquals(product.getId(), productUpdated.getId());
        assertEquals(product.getBalance(), productUpdated.getBalance());
    }

    @Test
    void deleteProduct()
    {
        Product product = ProductMother.getProductSavingsAccountActive();

        sut.deleteById(product.getId());

        assertThrows(ProductNotFound.class, () ->
                sut.findById(product.getId()));
    }

    @Test
    void deleteProductThrowProductNotFound()
    {
        Exception exception = assertThrows(ProductNotFound.class, () ->
                sut.deleteById(5L));

        String expectedMessage = "Product with ID '5' not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}