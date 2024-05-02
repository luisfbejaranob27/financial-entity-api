package co.luisfbejaranob.financial.entity.api.infrastructure.client.persistence.postgresql;

import co.luisfbejaranob.financial.entity.api.domain.client.Client;
import co.luisfbejaranob.financial.entity.api.shared.enums.IdentificationTypeEnum;

public final class ClientMappers
{
    private ClientMappers()
    {}

    static Client fromRaw(ClientEntity entity)
    {
        Client client = new Client();
        client.setId(entity.getId());
        client.setIdentificationType(entity.getIdentificationType().getName());
        client.setIdentificationNumber(entity.getIdentificationNumber());
        client.setNames(entity.getNames());
        client.setSurnames(entity.getSurnames());
        client.setEmail(entity.getEmail());
        client.setBirthDate(entity.getBirthDate());

        return client;
    }

    static ClientEntity from(Client client)
    {
        return ClientEntity.builder()
                .id(client.getId())
                .identificationType(IdentificationTypeEnum.valueOf(client.getIdentificationType()))
                .identificationNumber(client.getIdentificationNumber())
                .names(client.getNames())
                .surnames(client.getSurnames())
                .email(client.getEmail())
                .birthDate(client.getBirthDate())
                .build();
    }
}
