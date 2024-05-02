package co.luisfbejaranob.financial.entity.api.application.usecase;

import co.luisfbejaranob.financial.entity.api.domain.client.Client;
import co.luisfbejaranob.financial.entity.api.domain.client.ClientErrors.ClientInsufficientAge;
import co.luisfbejaranob.financial.entity.api.domain.client.ClientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class ClientUseCase
{
    private final ClientRepository repository;

    public ClientUseCase(ClientRepository repository)
    {
        this.repository = repository;
    }

    public Client findById(Long id)
    {
        return repository.findById(id);
    }

    public Client findByIdentificationNumber(String identificationNumber)
    {
        return repository.findByIdentificationNumber(identificationNumber);
    }

    public Client create(Client client)
    {
        if(isAdult(client))
        {
            return repository.create(client);
        }
        else
        {
            throw new ClientInsufficientAge();
        }
    }

    private boolean isAdult(Client client)
    {
        if(client.getBirthDate() != null)
        {
            LocalDate hoy = LocalDate.now();
            Period period = Period.between(client.getBirthDate(), hoy);

            return period.getYears() >= 18;
        }

        return false;
    }

    public Client update(Client client)
    {
        return repository.update(client);
    }

    public void deleteById(Long id)
    {
        repository.deleteById(id);
    }
}
