package sigym_rest.mapper;

import org.springframework.stereotype.Component;
import sigym_rest.dto.ProspectoDto;
import sigym_rest.model.Prospecto;

@Component
public class ProspectoMapper {

    public ProspectoDto toDto(Prospecto prospecto) {

        ProspectoDto dto = new ProspectoDto();

        dto.setIdProspecto(prospecto.getIdProspecto());
        dto.setNombre(prospecto.getNombre());
        dto.setFechaNacimiento(prospecto.getFechaNacimiento());
        dto.setTelefono(prospecto.getTelefono());
        dto.setCorreo(prospecto.getCorreo());
        dto.setTipoRegistro(prospecto.getTipoRegistro());

        return dto;
    }

    public Prospecto toEntity(ProspectoDto dto) {

        Prospecto prospecto = new Prospecto();

        prospecto.setIdProspecto(dto.getIdProspecto());
        prospecto.setNombre(dto.getNombre());
        prospecto.setFechaNacimiento(dto.getFechaNacimiento());
        prospecto.setTelefono(dto.getTelefono());
        prospecto.setCorreo(dto.getCorreo());
        prospecto.setTipoRegistro(dto.getTipoRegistro());

        return prospecto;
    }
}
