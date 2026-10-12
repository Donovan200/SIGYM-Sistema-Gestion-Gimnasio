package sigym_rest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sigym_rest.dto.ProspectoDto;
import sigym_rest.exception.ResourceNotFoundException;
import sigym_rest.mapper.ProspectoMapper;
import sigym_rest.model.Prospecto;
import sigym_rest.repository.ProspectoRepository;

import java.util.List;

@Slf4j
@Service
public class ProspectoService {

    private final ProspectoRepository prospectoRepository;
    private final ProspectoMapper prospectoMapper;

    public ProspectoService(ProspectoRepository prospectoRepository,
                            ProspectoMapper prospectoMapper) {
        this.prospectoRepository = prospectoRepository;
        this.prospectoMapper = prospectoMapper;
    }

    public List<ProspectoDto> findAll() {

        log.info("Consultando todos los prospectos");

        return prospectoRepository.findAll()
                .stream()
                .map(prospectoMapper::toDto)
                .toList();
    }

    public ProspectoDto findById(Long id) {

        log.info("Consultando prospecto con id: {}", id);

        Prospecto prospecto = prospectoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Prospecto no encontrado con id: {}", id);
                    return new ResourceNotFoundException(
                            "Prospecto no encontrado"
                    );
                });

        return prospectoMapper.toDto(prospecto);
    }

    public ProspectoDto save(ProspectoDto dto) {

        log.info(
                "Registrando nuevo prospecto: {}",
                dto.getNombre()
        );

        Prospecto prospecto =
                prospectoMapper.toEntity(dto);

        Prospecto guardado =
                prospectoRepository.save(prospecto);

        log.info(
                "Prospecto creado correctamente con id: {}",
                guardado.getIdProspecto()
        );

        return prospectoMapper.toDto(guardado);
    }

    public ProspectoDto update(
            Long id,
            ProspectoDto dto) {

        log.info(
                "Actualizando prospecto con id: {}",
                id
        );

        Prospecto prospecto =
                prospectoRepository.findById(id)
                        .orElseThrow(() -> {
                            log.warn(
                                    "No se encontró el prospecto con id: {}",
                                    id
                            );

                            return new ResourceNotFoundException(
                                    "Prospecto no encontrado"
                            );
                        });

        prospecto.setNombre(dto.getNombre());
        prospecto.setFechaNacimiento(dto.getFechaNacimiento());
        prospecto.setTelefono(dto.getTelefono());
        prospecto.setCorreo(dto.getCorreo());
        prospecto.setTipoRegistro(dto.getTipoRegistro());

        Prospecto actualizado =
                prospectoRepository.save(prospecto);

        log.info(
                "Prospecto actualizado correctamente con id: {}",
                actualizado.getIdProspecto()
        );

        return prospectoMapper.toDto(actualizado);
    }

    public void delete(Long id) {

        log.info(
                "Eliminando prospecto con id: {}",
                id
        );

        Prospecto prospecto =
                prospectoRepository.findById(id)
                        .orElseThrow(() -> {
                            log.warn(
                                    "No se encontró el prospecto con id: {}",
                                    id
                            );

                            return new ResourceNotFoundException(
                                    "Prospecto no encontrado"
                            );
                        });

        prospectoRepository.delete(prospecto);

        log.info(
                "Prospecto eliminado correctamente con id: {}",
                id
        );
    }
}