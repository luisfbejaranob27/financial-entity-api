package co.luisfbejaranob.financial.entity.api.infrastructure.transaction.persistence.postgresql;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTransactionRepository extends JpaRepository<TransactionEntity, Long>
{}
