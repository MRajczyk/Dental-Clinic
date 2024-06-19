package pl.dmcs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.domain.ActivationToken;
import pl.dmcs.domain.AppUser;
import pl.dmcs.repository.ActivationTokenRepository;
import pl.dmcs.repository.AppUserRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ActivationTokenService {

    @Autowired
    private ActivationTokenRepository activationTokenRepository;

    @Autowired
    private AppUserRepository userRepository;

    @Transactional
    public String createActivationToken(AppUser user) {
        String token = UUID.randomUUID().toString();
        ActivationToken activationToken = new ActivationToken();
        activationToken.setToken(token);
        activationToken.setUser(user);
        activationToken.setExpiryDate(LocalDateTime.now().plusMinutes(60));
        activationTokenRepository.save(activationToken);
        return token;
    }

    public Optional<ActivationToken> getActivationToken(String token) {
        return activationTokenRepository.findByToken(token);
    }

    @Scheduled(cron = "0 */5 * * * *") // Wykonaj co 5 minut
    @Transactional
    public void deleteExpiredTokens() {
        System.out.println("Deleting expired tokens");
        activationTokenRepository.deleteByExpiryDateBefore(LocalDateTime.now());
    }

    @Transactional
    public void delete(ActivationToken activationToken) {
        activationTokenRepository.delete(activationToken);
    }
}
