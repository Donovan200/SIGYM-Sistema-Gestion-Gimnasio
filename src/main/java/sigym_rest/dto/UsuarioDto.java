package sigym_rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioDto {

    private Long idUsuario;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(max = 50, message = "El usuario no puede superar 50 caracteres")
    private String username;

    private String password;

    @NotBlank(message = "El rol es obligatorio")
    private String rol;

    @NotNull(message = "El estado es obligatorio")
    private Boolean activo;

    public UsuarioDto() {
    }

    public UsuarioDto(Long idUsuario,
                      String username,
                      String password,
                      String rol,
                      Boolean activo) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.activo = activo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
