package sigym_rest.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sigym_rest.model.RefreshToken;
import sigym_rest.security.JwtService;
import sigym_rest.service.RefreshTokenService;

@Controller
public class WebAuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public WebAuthController(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            RefreshTokenService refreshTokenService) {

        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/web/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpServletResponse response) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );

        String accessToken =
                jwtService.generarToken(username);

        RefreshToken refreshToken =
                refreshTokenService.crearRefreshToken(username);

        Cookie accessCookie =
                new Cookie("access_token", accessToken);

        accessCookie.setHttpOnly(true);
        accessCookie.setPath("/");
        accessCookie.setMaxAge(15 * 60);

        Cookie refreshCookie =
                new Cookie(
                        "refresh_token",
                        refreshToken.getToken()
                );

        refreshCookie.setHttpOnly(true);
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge(24 * 60 * 60);

        response.addCookie(accessCookie);
        response.addCookie(refreshCookie);



        return "redirect:/";
    }

    @PostMapping("/web/logout")
    public String logout(HttpServletResponse response) {

        Cookie accessCookie = new Cookie("access_token", "");
        accessCookie.setHttpOnly(true);
        accessCookie.setPath("/");
        accessCookie.setMaxAge(0);

        Cookie refreshCookie = new Cookie("refresh_token", "");
        refreshCookie.setHttpOnly(true);
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge(0);

        response.addCookie(accessCookie);
        response.addCookie(refreshCookie);

        return "redirect:/login";
    }
}
