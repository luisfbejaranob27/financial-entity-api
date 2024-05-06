package co.luisfbejaranob.financial.entity.api.infrastructure.client.persistence.postgresql;

import co.luisfbejaranob.financial.entity.api.domain.client.Client;
import co.luisfbejaranob.financial.entity.api.domain.client.ClientErrors.ClientInsufficientAge;
import co.luisfbejaranob.financial.entity.api.domain.client.ClientErrors.ClientNotFound;
import co.luisfbejaranob.financial.entity.api.domain.client.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClientRepositoryImplTest
{
    private ClientRepository sut;

    @Autowired
    private JpaClientRepository repository;

    @BeforeEach
    void setUp()
    {
        sut = new ClientRepositoryImpl(repository);
    }

    @Test
    void findByIdFound()
    {
        Client client = ClientMother.getClient();

        Client clientFound = sut.findById(client.getId());

        assertEquals(client, clientFound);
    }

    @Test
    void findByIdThrowClientNotFound()
    {
        Exception exception = assertThrows(ClientNotFound.class, () -> {
            sut.findById(5L);
        });

        String expectedMessage = "Client with ID '5' not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void findByIdentificationNumberThrowClientNotFound()
    {
        Exception exception = assertThrows(ClientNotFound.class, () ->
                sut.findByIdentificationNumber("123456"));

        String expectedMessage = "Client with identification number '123456' not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createClient()
    {
        Client client = ClientMother.getPayloadNewClient();

        Client clientCreated = sut.create(client);

        assertNotNull(clientCreated.getId());
        assertEquals(client.getIdentificationType(), clientCreated.getIdentificationType());
        assertEquals(client.getIdentificationNumber(), clientCreated.getIdentificationNumber());
        assertEquals(client.getNames(), clientCreated.getNames());
        assertEquals(client.getSurnames(), clientCreated.getSurnames());
        assertEquals(client.getEmail(), clientCreated.getEmail());
        assertEquals(client.getBirthDate(), clientCreated.getBirthDate());
    }

    @Test
    void updateClient()
    {
        Client client = ClientMother.getClient();
        client.setEmail("luisfbejaranob27@gmail.com");
        Client clientUpdated = sut.update(client);

        assertEquals(client.getId() , clientUpdated.getId());
        assertEquals(client.getEmail(), clientUpdated.getEmail());
    }

    @Test
    void deleteClient()
    {
        Client client = ClientMother.getClient();

        sut.deleteById(client.getId());

        assertThrows(ClientNotFound.class, () ->
                sut.findById(client.getId()));
    }

    @Test
    void deleteClientThrowClientNotFound()
    {
        Exception exception = assertThrows(ClientNotFound.class, () ->
                sut.deleteById(5L));

        String expectedMessage = "Client with ID '5' not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}