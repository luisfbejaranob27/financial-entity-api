package co.luisfbejaranob.financial.entity.api.infrastructure.product.persistence.postgresql;

import co.luisfbejaranob.financial.entity.api.shared.enums.AccountTypeEnum;
import co.luisfbejaranob.financial.entity.api.shared.enums.StatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode

@Entity
@Table(name = "products")
public class ProductEntity
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AccountTypeEnum accountType;

    @Column(unique = true)
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Min(0)
    private Double balance;

    private Boolean gmfExempt;

    private Date createAt;

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
