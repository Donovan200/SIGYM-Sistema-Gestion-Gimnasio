package sigym_rest.mapper;

import org.springframework.stereotype.Component;
import sigym_rest.dto.ServicioDto;
import sigym_rest.model.Servicio;

@Component
public class ServicioMapper {

    public ServicioDto toDto(Servicio servicio) {

        ServicioDto dto = new ServicioDto();

        dto.setIdServicio(servicio.getIdServicio());
        dto.setNombre(servicio.getNombre());
        dto.setDescripcion(servicio.getDescripcion());
        dto.setPrecio(servicio.getPrecio());

        return dto;
    }

    public Servicio toEntity(ServicioDto dto) {

        Servicio servicio = new Servicio();

        servicio.setIdServicio(dto.getIdServicio());
        servicio.setNombre(dto.getNombre());
        servicio.setDescripcion(dto.getDescripcion());
        servicio.setPrecio(dto.getPrecio());

        return servicio;
    }
}