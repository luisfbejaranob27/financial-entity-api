package co.luisfbejaranob.financial.entity.api.infrastructure.client.persistence.postgresql;

import co.luisfbejaranob.financial.entity.api.domain.client.Client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public final class ClientMother
{
    private ClientMother()
    {}

    public static Client getPayloadClient()
    {
        return new Client("CC", "19093465", "Eliecer", "Bejarano", "eliecer.bejarano@gmail.com", LocalDate.parse("1949-10-10"));
    }

    public static Client getPayloadClientMinor()
    {
        return new Client("TI", "19860427", "Luna", "Bejarano", "luna.bejarano@gmail.com", LocalDate.parse("2007-06-01"));
    }

    public static Client getClient()
    {
        return new Client(1L, "CC", "1017128469", "Luis Ferley", "Bejarano Buritica", "luisfbejaranob@outlook.com", LocalDate.parse("1986-04-27"));
    }

    public static List<Client> getClientList()
    {
        return List.of(getClient());
    }
}
