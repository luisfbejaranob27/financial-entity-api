package co.luisfbejaranob.financial.entity.api.domain.client;

import java.util.Date;

public class Client
{
    private Long id;

    private String identificationType;

    private String identificationNumber;

    private String names;

    private String surnames;

    private String email;

    private Date birthDate;

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

    public Date getBirthDate()
    {
        return birthDate;
    }
    public void setBirthDate(Date birthDate)
    {
        this.birthDate = birthDate;
    }
}
