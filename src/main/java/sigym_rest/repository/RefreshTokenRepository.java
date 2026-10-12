package sigym_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sigym_rest.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository
        extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUsuario_IdUsuario(Long idUsuario);
}
