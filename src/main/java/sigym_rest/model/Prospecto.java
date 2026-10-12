package sigym_rest.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "prospecto")
public class Prospecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prospecto")
    private Long idProspecto;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(length = 15)
    private String telefono;

    @Column(length = 100, unique = true)
    private String correo;

    @Column(name = "tipo_registro", length = 30)
    private String tipoRegistro;

    @OneToMany(mappedBy = "prospecto")
    private List<Membresia> membresias;

    public List<Membresia> getMembresias() {
        return membresias;
    }

    public void setMembresias(List<Membresia> membresias) {
        this.membresias = membresias;
    }

    public Prospecto() {
    }

    public Prospecto(Long idProspecto, String nombre,
                     LocalDate fechaNacimiento, String telefono,
                     String correo, String tipoRegistro) {
        this.idProspecto = idProspecto;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.correo = correo;
        this.tipoRegistro = tipoRegistro;
    }

    public Long getIdProspecto() {
        return idProspecto;
    }

    public void setIdProspecto(Long idProspecto) {
        this.idProspecto = idProspecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    @Override
    public String toString() {
        return "Prospecto{" +
                "idProspecto=" + idProspecto +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", tipoRegistro='" + tipoRegistro + '\'' +
                '}';
    }
}

