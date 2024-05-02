package co.luisfbejaranob.financial.entity.api.application.usecase;

import co.luisfbejaranob.financial.entity.api.domain.client.Client;
import co.luisfbejaranob.financial.entity.api.domain.client.ClientErrors.ClientNotFound;
import co.luisfbejaranob.financial.entity.api.domain.client.ClientErrors.ClientInsufficientAge;
import co.luisfbejaranob.financial.entity.api.domain.client.ClientRepository;
import co.luisfbejaranob.financial.entity.api.infrastructure.client.persistence.postgresql.ClientMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class ClientUseCaseTest
{
    @Mock
    private ClientRepository repository;

    @InjectMocks
    private ClientUseCase sut;

    @Test
    void findById()
    {
        var reference = ClientMother.getClient();
        given(repository.findById(reference.getId())).willReturn(reference);

        Client client = sut.findById(reference.getId());

        assertEquals(reference, client);

        then(repository).should().findById(reference.getId());
    }

    @Test
    void findByIdentificationNumber()
    {
        var reference = ClientMother.getClient();
        given(repository.findByIdentificationNumber(reference.getIdentificationNumber())).willReturn(reference);

        var client = sut.findByIdentificationNumber(reference.getIdentificationNumber());

        assertEquals(reference, client);

        then(repository).should().findByIdentificationNumber(reference.getIdentificationNumber());
    }

    @Test
    void create()
    {
        var payload = ClientMother.getPayloadClient();
        var reference = ClientMother.getClient();
        given(repository.create(payload)).willReturn(reference);

        var client = sut.create(payload);

        assertEquals(reference, client);

        then(repository).should().create(payload);
    }

    @Test
    void createThrowClientInsufficientAge()
    {
        var payload = ClientMother.getPayloadClientMinor();

        assertThatThrownBy(() -> sut.create(payload)).isInstanceOf(ClientInsufficientAge.class);

        then(repository).should(never()).create(payload);
    }

    @Test
    void update()
    {
        var client = ClientMother.getClient();
        client.setEmail("luisfbejaranob27@gmail.com");
        given(repository.update(client)).willReturn(client);

        var clientUpdate = sut.update(client);

        assertEquals(client, clientUpdate);

        then(repository).should().update(client);
    }

    @Test
    void delete()
    {
        var client = ClientMother.getClient();
        given(repository.findById(client.getId())).willThrow(ClientNotFound.class);

        sut.deleteById(client.getId());

        assertThatThrownBy(() -> sut.findById(client.getId())).isInstanceOf(ClientNotFound.class);

        then(repository).should().deleteById(client.getId());
    }
}