package sigym_rest.service;

import org.springframework.stereotype.Service;
import sigym_rest.dto.MembresiaDto;
import sigym_rest.dto.PagoDto;
import sigym_rest.dto.ServicioDto;
import sigym_rest.exception.ResourceNotFoundException;
import sigym_rest.mapper.MembresiaMapper;
import sigym_rest.mapper.PagoMapper;
import sigym_rest.model.Membresia;
import sigym_rest.model.Prospecto;
import sigym_rest.model.Servicio;
import sigym_rest.repository.MembresiaRepository;
import sigym_rest.repository.PagoRepository;
import sigym_rest.repository.ProspectoRepository;
import sigym_rest.repository.ServicioRepository;
import sigym_rest.dto.ServicioDto;
import sigym_rest.mapper.ServicioMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class MembresiaService {

    private final MembresiaRepository membresiaRepository;
    private final ProspectoRepository prospectoRepository;
    private final ServicioRepository servicioRepository;
    private final PagoRepository pagoRepository;
    private final MembresiaMapper membresiaMapper;
    private final PagoMapper pagoMapper;
    private final ServicioMapper servicioMapper;

    public MembresiaService(MembresiaRepository membresiaRepository,
                            ProspectoRepository prospectoRepository,
                            ServicioRepository servicioRepository,
                            PagoRepository pagoRepository,
                            MembresiaMapper membresiaMapper,
                            PagoMapper pagoMapper,
                            ServicioMapper servicioMapper) {


        this.membresiaRepository = membresiaRepository;
        this.prospectoRepository = prospectoRepository;
        this.servicioRepository = servicioRepository;
        this.pagoRepository = pagoRepository;
        this.membresiaMapper = membresiaMapper;
        this.pagoMapper = pagoMapper;
        this.servicioMapper = servicioMapper;
    }

    public List<MembresiaDto> findAll() {
        return membresiaRepository.findAll()
                .stream()
                .map(membresiaMapper::toDto)
                .toList();
    }

    public MembresiaDto findById(Long id) {
        Membresia membresia = membresiaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Membresía no encontrada con id: " + id
                        ));

        return membresiaMapper.toDto(membresia);
    }

    public MembresiaDto save(MembresiaDto dto) {

        Prospecto prospecto = prospectoRepository
                .findById(dto.getIdProspecto())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Prospecto no encontrado con id: "
                                        + dto.getIdProspecto()
                        ));

        Membresia membresia = membresiaMapper.toEntity(dto);

        membresia.setProspecto(prospecto);

        if (dto.getIdsServicios() != null) {

            List<Servicio> servicios = new ArrayList<>();

            for (Long idServicio : dto.getIdsServicios()) {

                Servicio servicio = servicioRepository
                        .findById(idServicio)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Servicio no encontrado con id: "
                                                + idServicio
                                ));

                servicios.add(servicio);
            }

            membresia.setServicios(servicios);
        }

        Membresia guardada = membresiaRepository.save(membresia);

        return membresiaMapper.toDto(guardada);
    }

    public MembresiaDto update(Long id, MembresiaDto dto) {

        Membresia membresia = membresiaRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Membresía no encontrada con id: " + id
                        ));

        Prospecto prospecto = prospectoRepository
                .findById(dto.getIdProspecto())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Prospecto no encontrado con id: "
                                        + dto.getIdProspecto()
                        ));

        membresia.setTipo(dto.getTipo());
        membresia.setFechaInicio(dto.getFechaInicio());
        membresia.setFechaFin(dto.getFechaFin());
        membresia.setPrecio(dto.getPrecio());
        membresia.setProspecto(prospecto);

        if (dto.getIdsServicios() != null) {

            List<Servicio> servicios = new ArrayList<>();

            for (Long idServicio : dto.getIdsServicios()) {

                Servicio servicio = servicioRepository
                        .findById(idServicio)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Servicio no encontrado con id: "
                                                + idServicio
                                ));

                servicios.add(servicio);
            }

            membresia.setServicios(servicios);
        }

        Membresia actualizada =
                membresiaRepository.save(membresia);

        return membresiaMapper.toDto(actualizada);
    }

    public void delete(Long id) {

        Membresia membresia = membresiaRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Membresía no encontrada con id: " + id
                        ));

        membresiaRepository.delete(membresia);
    }

    public List<PagoDto> findPagosByMembresia(Long idMembresia) {

        membresiaRepository
                .findById(idMembresia)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Membresía no encontrada con id: "
                                        + idMembresia
                        ));

        return pagoRepository
                .findByMembresiaIdMembresia(idMembresia)
                .stream()
                .map(pagoMapper::toDto)
                .toList();
    }

    public PagoDto addPagoToMembresia(Long idMembresia, PagoDto dto) {

        Membresia membresia = membresiaRepository.findById(idMembresia)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Membresía no encontrada con id: " + idMembresia
                        ));

        dto.setIdMembresia(idMembresia);

        var pago = pagoMapper.toEntity(dto);
        pago.setMembresia(membresia);

        var guardado = pagoRepository.save(pago);

        return pagoMapper.toDto(guardado);
    }

    public void deletePagoFromMembresia(Long idMembresia, Long idPago) {

        Membresia membresia = membresiaRepository.findById(idMembresia)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Membresía no encontrada con id: " + idMembresia
                        ));

        var pago = pagoRepository.findById(idPago)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Pago no encontrado con id: " + idPago
                        ));

        if (pago.getMembresia() == null ||
                !pago.getMembresia().getIdMembresia().equals(membresia.getIdMembresia())) {

            throw new ResourceNotFoundException(
                    "El pago " + idPago +
                            " no pertenece a la membresía " + idMembresia
            );
        }

        pagoRepository.delete(pago);
    }

    public MembresiaDto addServicioToMembresia(Long idMembresia, Long idServicio) {

        Membresia membresia = membresiaRepository.findById(idMembresia)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Membresía no encontrada con id: " + idMembresia
                        ));

        Servicio servicio = servicioRepository.findById(idServicio)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Servicio no encontrado con id: " + idServicio
                        ));

        if (!membresia.getServicios().contains(servicio)) {
            membresia.getServicios().add(servicio);
        }

        Membresia actualizada = membresiaRepository.save(membresia);

        return membresiaMapper.toDto(actualizada);
    }

    public List<ServicioDto> findServiciosByMembresia(Long idMembresia) {

        Membresia membresia = membresiaRepository.findById(idMembresia)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Membresía no encontrada con id: " + idMembresia
                        ));

        return membresia.getServicios()
                .stream()
                .map(servicioMapper::toDto)
                .toList();
    }

    public void deleteServicioFromMembresia(Long idMembresia, Long idServicio) {

        Membresia membresia = membresiaRepository.findById(idMembresia)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Membresía no encontrada con id: " + idMembresia
                        ));

        Servicio servicio = servicioRepository.findById(idServicio)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Servicio no encontrado con id: " + idServicio
                        ));

        if (!membresia.getServicios().contains(servicio)) {
            throw new ResourceNotFoundException(
                    "El servicio " + idServicio +
                            " no está asociado a la membresía " + idMembresia
            );
        }

        membresia.getServicios().remove(servicio);

        membresiaRepository.save(membresia);
    }


}