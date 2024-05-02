package co.luisfbejaranob.financial.entity.api.infrastructure.client.persistence.postgresql;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaClientRepository extends JpaRepository<ClientEntity , Long>
{
    Optional<ClientEntity> findByIdentificationNumber(String identificationNumber);
}
