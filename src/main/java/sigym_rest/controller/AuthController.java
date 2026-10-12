package sigym_rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import sigym_rest.dto.JwtResponse;
import sigym_rest.dto.LoginRequest;
import sigym_rest.dto.RefreshTokenRequest;
import sigym_rest.model.RefreshToken;
import sigym_rest.security.JwtService;
import sigym_rest.service.RefreshTokenService;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public AuthController(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            RefreshTokenService refreshTokenService) {

        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(
            @RequestBody LoginRequest request) {

        log.info(
                "Intento de inicio de sesión para usuario: {}",
                request.getUsername()
        );

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        log.info(
                "Inicio de sesión exitoso para usuario: {}",
                request.getUsername()
        );

        String accessToken =
                jwtService.generarToken(
                        request.getUsername()
                );

        RefreshToken refreshToken =
                refreshTokenService
                        .crearRefreshToken(
                                request.getUsername()
                        );

        log.info(
                "Access Token y Refresh Token generados para usuario: {}",
                request.getUsername()
        );

        return ResponseEntity.ok(
                new JwtResponse(
                        accessToken,
                        refreshToken.getToken()
                )
        );
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refresh(
            @RequestBody RefreshTokenRequest request) {

        log.info("Solicitud de renovación de Access Token");

        RefreshToken refreshToken =
                refreshTokenService
                        .buscarPorToken(
                                request.getRefreshToken()
                        );

        refreshTokenService
                .verificarExpiracion(refreshToken);

        String username =
                refreshToken
                        .getUsuario()
                        .getUsername();

        String nuevoAccessToken =
                jwtService.generarToken(username);

        log.info(
                "Access Token renovado para usuario: {}",
                username
        );

        return ResponseEntity.ok(
                new JwtResponse(
                        nuevoAccessToken,
                        refreshToken.getToken()
                )
        );
    }
}