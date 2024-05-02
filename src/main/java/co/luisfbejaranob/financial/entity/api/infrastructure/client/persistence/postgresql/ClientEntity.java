package co.luisfbejaranob.financial.entity.api.infrastructure.client.persistence.postgresql;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

@Entity
@Table(name = "clients")
public class ClientEntity
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identificationType;

    private String identificationNumber;

    @Size(min = 2)
    private String names;

    @Size(min = 2)
    private String surnames;

    @Email
    private String email;

    private LocalDate birthDate;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "update_at")
    private Date updateAt;

    @PrePersist
    public void prePersist()
    {
        this.createAt = new Date();
    }

    @PreUpdate
    public void preUpdate()
    {
        this.updateAt = new Date();
    }
}
