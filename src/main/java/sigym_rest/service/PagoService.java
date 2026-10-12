package sigym_rest.service;

import org.springframework.stereotype.Service;
import sigym_rest.dto.PagoDto;
import sigym_rest.exception.ResourceNotFoundException;
import sigym_rest.mapper.PagoMapper;
import sigym_rest.model.Membresia;
import sigym_rest.model.Pago;
import sigym_rest.repository.MembresiaRepository;
import sigym_rest.repository.PagoRepository;

import java.util.List;

@Service
public class PagoService {

    private final PagoRepository pagoRepository;
    private final MembresiaRepository membresiaRepository;
    private final PagoMapper pagoMapper;

    public PagoService(PagoRepository pagoRepository,
                       MembresiaRepository membresiaRepository,
                       PagoMapper pagoMapper) {
        this.pagoRepository = pagoRepository;
        this.membresiaRepository = membresiaRepository;
        this.pagoMapper = pagoMapper;
    }

    public List<PagoDto> findAll() {
        return pagoRepository.findAll()
                .stream()
                .map(pagoMapper::toDto)
                .toList();
    }

    public PagoDto findById(Long id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Prospecto no encontrado"));

        return pagoMapper.toDto(pago);
    }

    public PagoDto save(PagoDto dto) {

        Membresia membresia = membresiaRepository
                .findById(dto.getIdMembresia())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Prospecto no encontrado"));

        Pago pago = pagoMapper.toEntity(dto);
        pago.setMembresia(membresia);

        Pago guardado = pagoRepository.save(pago);

        return pagoMapper.toDto(guardado);
    }

    public PagoDto update(Long id, PagoDto dto) {

        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Prospecto no encontrado"));

        Membresia membresia = membresiaRepository
                .findById(dto.getIdMembresia())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Prospecto no encontrado"));

        pago.setMonto(dto.getMonto());
        pago.setFechaPago(dto.getFechaPago());
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setMembresia(membresia);

        Pago actualizado = pagoRepository.save(pago);

        return pagoMapper.toDto(actualizado);
    }

    public void delete(Long id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Prospecto no encontrado"));

        pagoRepository.delete(pago);
    }
}