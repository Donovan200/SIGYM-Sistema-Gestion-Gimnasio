
package sigym_rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import sigym_rest.model.Usuario;
import sigym_rest.repository.UsuarioRepository;

@SpringBootApplication
public class SigymRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SigymRestApplication.class, args);
    }

    @Bean
    CommandLineRunner crearUsuarioAdmin(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            @Value("${admin.username}") String adminUsername,
            @Value("${admin.password}") String adminPassword) {

        return args -> {

            if (usuarioRepository.findByUsername(adminUsername).isEmpty()) {

                Usuario admin = Usuario.builder()
                        .username(adminUsername)
                        .password(passwordEncoder.encode(adminPassword))
                        .rol("ADMIN")
                        .activo(true)
                        .build();

                usuarioRepository.save(admin);

                System.out.println("Usuario administrador creado correctamente");
            }
        };
    }
}
