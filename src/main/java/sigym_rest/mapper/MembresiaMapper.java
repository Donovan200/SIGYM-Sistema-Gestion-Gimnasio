package sigym_rest.mapper;

import org.springframework.stereotype.Component;
import sigym_rest.dto.MembresiaDto;
import sigym_rest.model.Membresia;
import sigym_rest.model.Servicio;

@Component
public class MembresiaMapper {

    public MembresiaDto toDto(Membresia membresia) {

        MembresiaDto dto = new MembresiaDto();

        dto.setIdMembresia(membresia.getIdMembresia());
        dto.setTipo(membresia.getTipo());
        dto.setFechaInicio(membresia.getFechaInicio());
        dto.setFechaFin(membresia.getFechaFin());
        dto.setPrecio(membresia.getPrecio());

        if (membresia.getProspecto() != null) {

            dto.setIdProspecto(
                    membresia.getProspecto().getIdProspecto()
            );

            dto.setNombreProspecto(
                    membresia.getProspecto().getNombre()
            );
        }

        if (membresia.getServicios() != null) {

            dto.setIdsServicios(
                    membresia.getServicios()
                            .stream()
                            .map(Servicio::getIdServicio)
                            .toList()
            );

            dto.setNombresServicios(
                    membresia.getServicios()
                            .stream()
                            .map(Servicio::getNombre)
                            .toList()
            );
        }

        return dto;
    }

    public Membresia toEntity(MembresiaDto dto) {

        Membresia membresia = new Membresia();

        membresia.setIdMembresia(dto.getIdMembresia());
        membresia.setTipo(dto.getTipo());
        membresia.setFechaInicio(dto.getFechaInicio());
        membresia.setFechaFin(dto.getFechaFin());
        membresia.setPrecio(dto.getPrecio());

        return membresia;
    }
}