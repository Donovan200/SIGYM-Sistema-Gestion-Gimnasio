package sigym_rest.service;

import org.springframework.stereotype.Service;
import sigym_rest.dto.ServicioDto;
import sigym_rest.exception.ResourceNotFoundException;
import sigym_rest.mapper.ServicioMapper;
import sigym_rest.model.Servicio;
import sigym_rest.repository.ServicioRepository;

import java.util.List;

@Service
public class ServicioService {

    private final ServicioRepository servicioRepository;
    private final ServicioMapper servicioMapper;

    public ServicioService(ServicioRepository servicioRepository,
                           ServicioMapper servicioMapper) {
        this.servicioRepository = servicioRepository;
        this.servicioMapper = servicioMapper;
    }

    public List<ServicioDto> findAll() {
        return servicioRepository.findAll()
                .stream()
                .map(servicioMapper::toDto)
                .toList();
    }

    public ServicioDto findById(Long id) {
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Prospecto no encontrado"));

        return servicioMapper.toDto(servicio);
    }

    public ServicioDto save(ServicioDto dto) {
        Servicio servicio = servicioMapper.toEntity(dto);
        Servicio guardado = servicioRepository.save(servicio);

        return servicioMapper.toDto(guardado);
    }

    public ServicioDto update(Long id, ServicioDto dto) {

        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Prospecto no encontrado"));
        servicio.setNombre(dto.getNombre());
        servicio.setDescripcion(dto.getDescripcion());
        servicio.setPrecio(dto.getPrecio());

        Servicio actualizado = servicioRepository.save(servicio);

        return servicioMapper.toDto(actualizado);
    }

    public void delete(Long id) {
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Prospecto no encontrado"));
        servicioRepository.delete(servicio);
    }
}
