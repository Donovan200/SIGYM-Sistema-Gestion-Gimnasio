package sigym_rest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sigym_rest.dto.UsuarioDto;
import sigym_rest.exception.ResourceNotFoundException;
import sigym_rest.model.Usuario;
import sigym_rest.repository.UsuarioRepository;

import java.util.List;

@Slf4j
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioDto> findAll() {

        return usuarioRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public UsuarioDto findById(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado"
                        )
                );

        return toDto(usuario);
    }

    public UsuarioDto save(UsuarioDto dto) {

        Usuario usuario = new Usuario();

        usuario.setUsername(dto.getUsername());

        usuario.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );

        usuario.setRol(dto.getRol());
        usuario.setActivo(dto.getActivo());

        Usuario guardado =
                usuarioRepository.save(usuario);

        log.info(
                "Usuario creado correctamente con id: {}",
                guardado.getIdUsuario()
        );

        return toDto(guardado);
    }

    public UsuarioDto update(Long id, UsuarioDto dto) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado"
                        )
                );

        usuario.setUsername(dto.getUsername());
        usuario.setRol(dto.getRol());
        usuario.setActivo(dto.getActivo());

        if (dto.getPassword() != null
                && !dto.getPassword().isBlank()) {

            usuario.setPassword(
                    passwordEncoder.encode(dto.getPassword())
            );
        }

        Usuario actualizado =
                usuarioRepository.save(usuario);

        log.info(
                "Usuario actualizado correctamente con id: {}",
                actualizado.getIdUsuario()
        );

        return toDto(actualizado);
    }

    public void delete(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado"
                        )
                );

        usuarioRepository.delete(usuario);

        log.info(
                "Usuario eliminado correctamente con id: {}",
                id
        );
    }

    private UsuarioDto toDto(Usuario usuario) {

        UsuarioDto dto = new UsuarioDto();

        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setUsername(usuario.getUsername());

        // No enviamos el hash de contraseña al navegador.
        dto.setPassword(null);

        dto.setRol(usuario.getRol());
        dto.setActivo(usuario.getActivo());

        return dto;
    }
}
