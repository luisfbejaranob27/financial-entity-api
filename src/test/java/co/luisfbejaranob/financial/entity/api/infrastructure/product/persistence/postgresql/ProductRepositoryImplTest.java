package co.luisfbejaranob.financial.entity.api.infrastructure.product.persistence.postgresql;

import co.luisfbejaranob.financial.entity.api.domain.product.Product;
import co.luisfbejaranob.financial.entity.api.domain.product.ProductErrors.ProductNotFound;
import co.luisfbejaranob.financial.entity.api.domain.product.ProductRepository;
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
    private JpaProductRepository repository;

    @BeforeEach
    void setUp()
    {
        sut = new ProductRepositoryImpl(repository);
    }

    @Test
    void findProductByIdFound()
    {
        Product product = ProductMother.getProductCurrentAccountActive();

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
        Product product = ProductMother.getPayloadProductCurrentAccount();

        Product productCreated = sut.create(product);

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
        Product product = ProductMother.getProductCurrentAccountActive();
        product.setBalance(1200000.0);

        Product productUpdated = sut.update(product);

        assertEquals(product.getId(), productUpdated.getId());
        assertEquals(product.getBalance(), productUpdated.getBalance());
    }

    @Test
    void deleteProduct()
    {
        Product product = ProductMother.getProductCurrentAccountActive();

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