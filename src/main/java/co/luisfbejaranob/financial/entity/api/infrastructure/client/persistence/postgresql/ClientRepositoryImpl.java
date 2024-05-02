package co.luisfbejaranob.financial.entity.api.infrastructure.client.persistence.postgresql;

import co.luisfbejaranob.financial.entity.api.domain.client.Client;
import co.luisfbejaranob.financial.entity.api.domain.client.ClientErrors.ClientInsufficientAge;
import co.luisfbejaranob.financial.entity.api.domain.client.ClientErrors.ClientNotFound;
import co.luisfbejaranob.financial.entity.api.domain.client.ClientRepository;
import co.luisfbejaranob.financial.entity.api.shared.enums.IdentificationTypeEnum;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Period;

import static co.luisfbejaranob.financial.entity.api.infrastructure.client.persistence.postgresql.ClientMappers.from;
import static co.luisfbejaranob.financial.entity.api.infrastructure.client.persistence.postgresql.ClientMappers.fromRaw;

@Repository
public class ClientRepositoryImpl implements ClientRepository
{
    private final JpaClientRepository jpaRepository;

    public ClientRepositoryImpl(JpaClientRepository jpaRepository)
    {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Client findById(Long id)
    {
        return fromRaw(
                jpaRepository.findById(id)
                        .orElseThrow(() -> new ClientNotFound(id))
        );
    }

    @Override
    public Client findByIdentificationNumber(String identificationNumber)
    {
        return fromRaw(
                jpaRepository.findByIdentificationNumber(identificationNumber)
                        .orElseThrow(() -> new ClientNotFound(identificationNumber))
        );
    }

    @Override
    public Client create(Client client)
    {
        if(isAdult(client))
        {
            return fromRaw(
                    jpaRepository.save(from(client))
            );
        }
        else
        {
            throw new ClientInsufficientAge();
        }
    }

    @Override
    public Client update(Client client)
    {
        ClientEntity clientBd = jpaRepository.findById(client.getId())
                .orElseThrow(() -> new ClientNotFound(client.getId()));

        return fromRaw(
                jpaRepository.save(updateValues(clientBd , client))
        );
    }

    private ClientEntity updateValues(ClientEntity clientBd, Client client)
    {
        if(client.getIdentificationType() != null)
        {
            clientBd.setIdentificationType(IdentificationTypeEnum.valueOf(client.getIdentificationType()));
        }
        if(client.getIdentificationNumber() != null)
        {
            clientBd.setIdentificationNumber(client.getIdentificationNumber());
        }
        if(client.getNames() != null)
        {
            clientBd.setNames(client.getNames());
        }
        if(client.getSurnames() != null)
        {
            clientBd.setSurnames(client.getSurnames());
        }
        if(client.getEmail() != null)
        {
            clientBd.setEmail(client.getEmail());
        }
        if(client.getBirthDate() != null)
        {
            clientBd.setBirthDate(client.getBirthDate());
        }

        return clientBd;
    }

    @Override
    public void deleteById(Long id)
    {
        boolean exists = jpaRepository.existsById(id);

        if(exists)
        {
            jpaRepository.deleteById(id);
        }
        else
        {
            throw new ClientNotFound(id);
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
}
