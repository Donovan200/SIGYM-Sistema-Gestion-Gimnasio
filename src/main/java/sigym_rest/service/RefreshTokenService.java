package sigym_rest.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sigym_rest.model.RefreshToken;
import sigym_rest.model.Usuario;
import sigym_rest.repository.RefreshTokenRepository;
import sigym_rest.repository.UsuarioRepository;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UsuarioRepository usuarioRepository;

    private static final long DURACION_REFRESH_TOKEN =
            1000L * 60 * 60 * 24; // 24 horas

    public RefreshTokenService(
            RefreshTokenRepository refreshTokenRepository,
            UsuarioRepository usuarioRepository) {

        this.refreshTokenRepository = refreshTokenRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public RefreshToken crearRefreshToken(String username) {

        Usuario usuario = usuarioRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado")
                );

        RefreshToken refreshToken =
                refreshTokenRepository
                        .findByUsuario_IdUsuario(usuario.getIdUsuario())
                        .orElse(
                                RefreshToken.builder()
                                        .usuario(usuario)
                                        .build()
                        );

        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken.setFechaExpiracion(
                Instant.now().plusMillis(DURACION_REFRESH_TOKEN)
        );

        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken buscarPorToken(String token) {

        return refreshTokenRepository
                .findByToken(token)
                .orElseThrow(() ->
                        new RuntimeException("Refresh Token no encontrado")
                );
    }

    public RefreshToken verificarExpiracion(
            RefreshToken refreshToken) {

        if (refreshToken.getFechaExpiracion()
                .isBefore(Instant.now())) {

            refreshTokenRepository.delete(refreshToken);

            throw new RuntimeException(
                    "El Refresh Token ha expirado"
            );
        }

        return refreshToken;
    }
}
