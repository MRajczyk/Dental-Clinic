package pl.dmcs.repository;

import pl.dmcs.domain.ActivationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.time.LocalDateTime;

public interface ActivationTokenRepository extends JpaRepository<ActivationToken, Long> {
    Optional<ActivationToken> findByToken(String token);
    void deleteByExpiryDateBefore(LocalDateTime now);
}