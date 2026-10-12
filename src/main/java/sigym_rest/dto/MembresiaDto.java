package sigym_rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.List;

public class MembresiaDto {

    private Long idMembresia;

    @NotBlank(message = "El tipo de membresía es obligatorio")
    private String tipo;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    private LocalDate fechaFin;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a cero")
    private Double precio;

    @NotNull(message = "El id del prospecto es obligatorio")
    private Long idProspecto;
    private String nombreProspecto;

    private List<Long> idsServicios;
    private List<String> nombresServicios;

    public List<String> getNombresServicios() {
        return nombresServicios;
    }

    public void setNombresServicios(List<String> nombresServicios) {
        this.nombresServicios = nombresServicios;
    }

    public MembresiaDto() {
    }

    public MembresiaDto(Long idMembresia,
                        String tipo,
                        LocalDate fechaInicio,
                        LocalDate fechaFin,
                        Double precio,
                        Long idProspecto,
                        List<Long> idsServicios) {
        this.idMembresia = idMembresia;
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precio = precio;
        this.idProspecto = idProspecto;
        this.idsServicios = idsServicios;
    }

    public Long getIdMembresia() {
        return idMembresia;
    }

    public void setIdMembresia(Long idMembresia) {
        this.idMembresia = idMembresia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Long getIdProspecto() {
        return idProspecto;
    }

    public void setIdProspecto(Long idProspecto) {
        this.idProspecto = idProspecto;
    }

    public List<Long> getIdsServicios() {
        return idsServicios;
    }

    public void setIdsServicios(List<Long> idsServicios) {
        this.idsServicios = idsServicios;
    }

    public String getNombreProspecto() {
        return nombreProspecto;
    }

    public void setNombreProspecto(String nombreProspecto) {
        this.nombreProspecto = nombreProspecto;
    }
}
