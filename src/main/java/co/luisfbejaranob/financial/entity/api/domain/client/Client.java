package co.luisfbejaranob.financial.entity.api.domain.client;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Client
{
    private Long id;

    private String identificationType;

    private String identificationNumber;

    private String names;

    private String surnames;

    private String email;

    private LocalDate birthDate;

    public Client()
    {}

    public Client(String identificationType,
                  String identificationNumber,
                  String names,
                  String surnames,
                  String email,
                  LocalDate birthDate
    )
    {
        this.identificationType = identificationType;
        this.identificationNumber = identificationNumber;
        this.names = names;
        this.surnames = surnames;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Client(Long id,
                  String identificationType,
                  String identificationNumber,
                  String names,
                  String surnames,
                  String email,
                  LocalDate birthDate
    )
    {
        this.id = id;
        this.identificationType = identificationType;
        this.identificationNumber = identificationNumber;
        this.names = names;
        this.surnames = surnames;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }

    public String getIdentificationType()
    {
        return identificationType;
    }
    public void setIdentificationType(String identificationType)
    {
        this.identificationType = identificationType;
    }

    public String getIdentificationNumber()
    {
        return identificationNumber;
    }
    public void setIdentificationNumber(String identificationNumber)
    {
        this.identificationNumber = identificationNumber;
    }

    public String getNames()
    {
        return names;
    }
    public void setNames(String names)
    {
        this.names = names;
    }

    public String getSurnames()
    {
        return surnames;
    }
    public void setSurnames(String surnames)
    {
        this.surnames = surnames;
    }

    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public LocalDate getBirthDate()
    {
        return birthDate;
    }
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public void setBirthDate(LocalDate birthDate)
    {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) &&
                Objects.equals(identificationType, client.identificationType) &&
                Objects.equals(identificationNumber, client.identificationNumber) &&
                Objects.equals(names, client.names) &&
                Objects.equals(surnames, client.surnames) &&
                Objects.equals(email, client.email) &&
                Objects.equals(birthDate, client.birthDate);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(
                id,
                identificationType,
                identificationNumber,
                names,
                surnames,
                email,
                birthDate
        );
    }
}
