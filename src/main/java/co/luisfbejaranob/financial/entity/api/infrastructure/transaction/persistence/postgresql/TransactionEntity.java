package co.luisfbejaranob.financial.entity.api.infrastructure.transaction.persistence.postgresql;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode

@Entity
@Table(name = "transactions")
public class TransactionEntity
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionType;

    private String originAccount;

    private String destinationAccount;

    private Double amount;
}
