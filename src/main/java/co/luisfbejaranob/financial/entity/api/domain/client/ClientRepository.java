package co.luisfbejaranob.financial.entity.api.domain.client;

public interface ClientRepository
{
    Client findById(Long id);

    Client findByIdentificationNumber(String identificationNumber);

    Client create(Client client);

    Client update(Client client);

    void deleteById(Long id);
}
