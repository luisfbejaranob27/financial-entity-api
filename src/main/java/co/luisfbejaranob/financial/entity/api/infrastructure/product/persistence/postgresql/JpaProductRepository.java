package co.luisfbejaranob.financial.entity.api.infrastructure.product.persistence.postgresql;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaProductRepository extends JpaRepository<ProductEntity , Long>
{
    Optional<ProductEntity> findByAccountNumber(String accountNumber);
}
