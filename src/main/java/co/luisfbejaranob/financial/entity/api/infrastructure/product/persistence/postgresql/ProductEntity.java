package co.luisfbejaranob.financial.entity.api.infrastructure.product.persistence.postgresql;

import co.luisfbejaranob.financial.entity.api.domain.client.Client;
import co.luisfbejaranob.financial.entity.api.domain.transaction.Transaction;
import co.luisfbejaranob.financial.entity.api.infrastructure.client.persistence.postgresql.ClientEntity;
import co.luisfbejaranob.financial.entity.api.infrastructure.transaction.persistence.postgresql.TransactionEntity;
import co.luisfbejaranob.financial.entity.api.shared.enums.AccountTypeEnum;
import co.luisfbejaranob.financial.entity.api.shared.enums.StatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
import java.util.List;

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
    @Size(min = 10, max = 10)
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Min(0)
    private Double balance;

    private Boolean gmfExempt;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "product")
    private List<TransactionEntity> transactions;

    @OneToOne(optional = false)
    private ClientEntity client;

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
